package ampath.or.ke.spot.controllers;


import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.models.SMTPServer;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.CountiesService;
import ampath.or.ke.spot.services.ProgramsService;
import ampath.or.ke.spot.services.SMTPServerService;
import ampath.or.ke.spot.services.UserService;
import ampath.or.ke.spot.utils.HtmlEmails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    public UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SMTPServerService smtpServerService;
    @Autowired
     private ProgramsService programsService;
     @Autowired
     private CountiesService countiesService;

    @RequestMapping(value = "/users")
    //@ResponseBody
    public ModelAndView UserDetails(HttpSession session) {
        if (session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();

            ModelAndView modelAndView = new ModelAndView();
            List<User> userList = userService.getAllUsers();

            modelAndView.addObject("userslist",userList);
            modelAndView.addObject("users", new User());
            modelAndView.addObject("partner",programsService.getAllPrograms());
            modelAndView.addObject("counties",countiesService.getAllDataset());
            modelAndView.addObject("smg", session.getAttribute("smg"));
            modelAndView.setViewName("user");
            return modelAndView;
       } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }

    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("fullname") String fullname,
                         @RequestParam("username") String username,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         HttpSession session){
        if (session.getAttribute("user") != null) {
            // LoginController.createuser(fullname,username,email,password,session);
            //String newpass = "";
            //newpass = bCryptPasswordEncoder.encode(password);
            
            UUID uuid = UUID.randomUUID();
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();
            ModelAndView modelAndView = new ModelAndView();
            User user = new User();
            user.setEmail(email);
            user.setFull_name(fullname);
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus(1);
            user.setCreated_by(userdetails.getId());
            user.setCreated_on(nowDate);
            user.setUuid(uuid.toString());
            userService.saveUser(user);
            SMTPServer smtpServers = smtpServerService.getByTopOne();
            // System.out.print(" Host ndo hii "+host);
            if(smtpServers !=null) {
                String subject = " Reporting Server User ";
                String message = "Dear "+ fullname + "\r\n";
                message += "\r\n";
                message +="We wish to notify you that your account have been created on the reporting server."+ "\r\n";
                message += "\r\n";
                message += "User credential are as follows: \n";
                message += "Username: "+ username + "\n";
                message += "Password: "+ password + "\n";
                message += "Url http://portal.karp.or.ke/middleware \n";
                message += "\r\n";
                message += "Regards"+ "\r\n";
                message += "Dev & Reporting Team"+ "\r\n";
                ApplicationMailer.sendMail(email, subject, message, smtpServers);
            }

            return "added successfully.";
        } else {
            return "Login again";
        }
    }

    @RequestMapping(value = "/period")
    public ModelAndView Period(HttpSession session) {
        if (session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();
            ModelAndView modelAndView = new ModelAndView();
            List<User> userList = userService.getAllUsers();
            modelAndView.addObject("userslist",userList);
          //  modelAndView.addObject("period",reportingPeriodService.getAllCurrentAndPast(5));
            modelAndView.setViewName("pm/reportingperiod");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }

    @RequestMapping(value = "/users/changepassword")
    public ModelAndView resetpassword(HttpSession session) {
        if (session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("user",userdetails);
            modelAndView.addObject("msg","0");
            modelAndView.addObject("username",userdetails.getFull_name());
             modelAndView.addObject("email",userdetails.getEmail());
            modelAndView.setViewName("resetpass");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
   
    @RequestMapping(value = "/users/changepassword", method = RequestMethod.POST)
    public ModelAndView potresetpassword(@Valid User user, BindingResult bindingResult,HttpSession session) {
        if (session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user",userdetails);
            modelAndView.addObject("msg","1");
            modelAndView.addObject("username",userdetails.getFull_name());
             modelAndView.addObject("email",userdetails.getEmail());
            modelAndView.setViewName("resetpass");
             System.out.print(bindingResult+"Is your user "+ user.getPassword());
             SMTPServer smtpServers = smtpServerService.getByTopOne();
             String fullname =userdetails.getFull_name();
             String email =userdetails.getEmail();

             String newpass = "";
                //String uid = userdetails.getId());
                String password = user.getPassword();
                newpass = bCryptPasswordEncoder.encode(password);
                userdetails.setPassnew(0);
                userdetails.setStatus(1);
                userdetails.setPassword(newpass);
                userService.updateUser(userdetails);
        
            if(smtpServers !=null) {
                String subject = " SPOT Password Creation";
                String vlink ="https://spot.ampath.or.ke";
                String message =  HtmlEmails.generateLoginemail(fullname,vlink);
                ApplicationMailer.sendMail(email, subject, message, smtpServers);
            }
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
    @RequestMapping(value = "/users/createaccount", method = RequestMethod.POST)
    public ModelAndView createaccount(@Valid User user, BindingResult bindingResult,HttpSession session) {
        if (session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user",userdetails);
            modelAndView.addObject("msg","1");
            modelAndView.addObject("username",userdetails.getFull_name());
             modelAndView.addObject("email",userdetails.getEmail());
            modelAndView.setViewName("user");
              UUID uuid = UUID.randomUUID();
              user.setUuid( String.valueOf(uuid));
                String newpass = "";
                String password = "Spot@2023";
                newpass = bCryptPasswordEncoder.encode(password);
                user.setPassnew(1);
                user.setStatus(0);
                user.setPassword(newpass);
                userService.saveUser(user);

                String vallink = "https://spot.ampath.or.ke/system/users/validate/"+uuid.toString();
            
             SMTPServer smtpServers = smtpServerService.getByTopOne();
             String fullname =user.getFull_name();
             String email =user.getEmail();
        
            if(smtpServers !=null) {
                String subject = " SPOT Account Creation";
                 String message =  HtmlEmails.generateResetpassemail(fullname,vallink);
                 ApplicationMailer.sendMail(email, subject, message, smtpServers);
            }
            System.out.println("Link is here "+ vallink);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
    @RequestMapping(value="/users/validate/{uuid}", method = RequestMethod.GET)
    public ModelAndView validate(@PathVariable String uuid,HttpSession session ){
     ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByUuid(uuid);
         Programs companyDetails = programsService.getPrograms(user.getPid());
            session.setAttribute("user", user);
            session.setAttribute("companydetails", companyDetails);
        int passnew = user.getPassnew();
        int status = user.getStatus();
        int msg =0;
        if(passnew==1 &&status==0){
            msg=1;
        }else{
            msg=0;
        }
            modelAndView.addObject("msg",msg);
            modelAndView.addObject("user",user);
            modelAndView.addObject("username",user.getFull_name());
            modelAndView.addObject("email",user.getEmail());
            modelAndView.setViewName("validate");
        return  modelAndView;
    }
}

