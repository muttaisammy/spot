package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Programs;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Transactional
@RequestMapping("/service")
public class ServiceController {
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView postss(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("service_tracker");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/setup");
        }
    }
}
