package ampath.or.ke.spot.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ampath.or.ke.spot.models.Role;
import ampath.or.ke.spot.models.SMTPServer;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.ProgramsService;
import ampath.or.ke.spot.services.SMTPServerService;
import ampath.or.ke.spot.services.UserService;
import ampath.or.ke.spot.utils.HtmlEmails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

import static ampath.or.ke.spot.utils.GlobalVars.generateSecurePassword;

@Controller
@Transactional
@RequestMapping("/auth")
@Scope("session")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramsService programsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SMTPServerService smtpServerService;
    @Autowired
    private AuthenticationManager authManager;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session ){
        if(session.getAttribute("company")!=null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("auth");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }

    @RequestMapping(value="/logins", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(required=false, value="email") String username,
                                     @RequestParam(required=false, value="password") String password,
                                     HttpSession session)  {
        if(session.getAttribute("company")!=null) {
            ModelAndView modelAndView = new ModelAndView();
            System.out.println("Imefika Hapa");
           
            modelAndView.setViewName("login");
            User usernameExist = userService.findUserByEmailORUsername(username,username);
            if (usernameExist != null) {
                boolean authcheck = bCryptPasswordEncoder.matches(password, usernameExist.getPassword());
                if (authcheck == true) {

                    int passnew = usernameExist.getPassnew();
                    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                    for (Role role : usernameExist.getRoles()) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
                    }

                    UsernamePasswordAuthenticationToken authReq =
                            new UsernamePasswordAuthenticationToken(usernameExist.getUsername(),password,grantedAuthorities);

                    Authentication auth = authManager.authenticate(authReq);
                    SecurityContext sc = SecurityContextHolder.getContext();
                    sc.setAuthentication(auth); 
                    System.out.println("Imeingia hapa"+ passnew);

                    return new ModelAndView("redirect:/process");

                }else{
                    System.out.println("Error Iko "+ username +" pass "+password);
                    return new ModelAndView("redirect:/auth/login?error=true");
                }
            }else{
                System.out.println("Error Iko "+ username +" pass "+password);
                return new ModelAndView("redirect:/auth/login?error=true");
            }

        }else{
            return new ModelAndView("redirect:/setup");
        }
    }

    @RequestMapping(value="/reset-password", method = RequestMethod.GET)
    public ModelAndView reset(HttpSession session ){
        if(session.getAttribute("company")!=null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", new User());
             modelAndView.addObject("msg", 0);
            modelAndView.setViewName("forgot-password");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }

    @RequestMapping(value="/reset-password", method = RequestMethod.POST)
    public ModelAndView resetpass(@Valid User user, BindingResult bindingResult,HttpSession session){
        if(session.getAttribute("company")!=null) {
            ModelAndView modelAndView = new ModelAndView();
            System.out.println("Password reset email "+user.getEmail());
            String email = user.getEmail();
            User activateuser = userService.findUserByEmail(email);
            if (activateuser !=null){
                String newpass = "";
                String uid = Integer.toString(activateuser.getId());
                String password = generateSecurePassword();
                newpass = bCryptPasswordEncoder.encode(password);
                activateuser.setPassnew(1);
                activateuser.setStatus(0);
               // activateuser.setPassword(newpass);
                userService.updateUser(activateuser);
                String uuid =activateuser.getUuid();

                String vallink = "https://spot.ampath.or.ke/system/users/validate/"+uuid.toString();
            
             SMTPServer smtpServers = smtpServerService.getByTopOne();
             String fullname =activateuser.getFull_name();
            
               int msg=0;
            
            if(smtpServers !=null) {
                String subject = " SPOT Forgot Password Alert";
                 String message =  HtmlEmails.generateforgotpassemail(fullname,vallink);
                 ApplicationMailer.sendMail(email, subject, message, smtpServers);
                 System.out.println("Link is here "+ vallink);
                 modelAndView.addObject("username",activateuser.getFull_name());
                 modelAndView.addObject("email",activateuser.getEmail());
                 modelAndView.addObject("msg",1 );
            }
                
            }else
            {
                 modelAndView.addObject("username",activateuser.getFull_name());
                 modelAndView.addObject("email",activateuser.getEmail());
                modelAndView.addObject("msg", 0);
            }

            modelAndView.setViewName("forgot-password");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }

}
