package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Periods;
import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.PeriodServices;
import ampath.or.ke.spot.services.ProgramsService;
import ampath.or.ke.spot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("session")
public class RootController {
    @Autowired
    private ProgramsService programsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PeriodServices periodServices;
    private String checkstring;

        @RequestMapping(value="/", method = RequestMethod.GET)
        public ModelAndView maindash( HttpSession session) throws InterruptedException, IOException {
           if(session.getAttribute("company")!=null && session.getAttribute("user") != null ) {
            ModelAndView modelAndView = new ModelAndView();
            return new ModelAndView("redirect:/spot/dashboard");
        
            }
            else{
                return new ModelAndView("redirect:/setup");
            } 
        }

    @RequestMapping(value = {"/setup"}, method = RequestMethod.POST)
    public ModelAndView newsetup(@Valid Programs company, BindingResult bindingResult,
                                 @RequestParam(required = false, value = "programname") String cname,
                                 @RequestParam(required = false, value = "fullname") String fullname,
                                 @RequestParam(required = false, value = "email") String email,
                                 @RequestParam(required = false, value = "username") String username,
                                 @RequestParam(required = false, value = "password") String password
    ) throws IOException {
        User users = new User();
        users.setUsername(username);
        users.setEmail(email);
        users.setFull_name(fullname);
        users.setPassword(password);
        users.setStatus(1);
        company.setStatus(1);

        programsService.save(company);
        userService.saveUser(users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("company", new Programs());
        return new ModelAndView("redirect:/setup");

    }

    @RequestMapping(value = {"/setup"}, method = RequestMethod.GET)
    public ModelAndView setup(HttpSession sessionsetup) throws InterruptedException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        List<Programs> checkCompany = programsService.getAllPrograms();
        if (checkCompany.size() > 0) {
            Integer check2 = checkCompany.get(0).getStatus();
            sessionsetup.setAttribute("company", checkCompany.get(0).getProgramname());
            System.out.print("Check ndo Hii " + check2);
            // check2.equals(0)
            if (check2.equals(0)) {
                return new ModelAndView("redirect:/auth/activate");
            } else {
                /// return new ModelAndView("redirect:/auth/activate");

                return new ModelAndView("redirect:/auth/login");
            }
        } else {
            modelAndView.addObject("program", new Programs());
            modelAndView.addObject("scheck", sessionsetup.getAttribute("checkstring"));
            modelAndView.setViewName("setup");
            return modelAndView;
        }


    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public ModelAndView process(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth. "+ auth.getName()+" "+auth.getDetails());
        User user = userService.findUserByEmailORUsername(auth.getName(),auth.getName());
        String views = "";
        if ((session.getAttribute("company").toString() == null)) {
            return new ModelAndView("redirect:/setup");
        } else {
            Programs companyDetails = programsService.getPrograms(user.getPid());
            session.setAttribute("user", user);
            session.setAttribute("companydetails", companyDetails);

            Periods cp = periodServices.getCurrentPeriod();
            if (cp != null) {
                session.setAttribute("cm", cp.getMonth());
                session.setAttribute("cy", cp.getYear());
            } else {
                session.setAttribute("cm", "Set");
                session.setAttribute("cy", "Now");
            }

            System.out.println("User is hers "+ auth.isAuthenticated()+" auth name "+auth.getName());
            views = "redirect:/spot/dashboard";
        }

         return new ModelAndView(views);
}
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView admin(HttpSession sessionsetup) throws InterruptedException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        return new ModelAndView("redirect:/auth/login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session)  {
        session.invalidate();
        return new ModelAndView("redirect:/setup");
    }




}
