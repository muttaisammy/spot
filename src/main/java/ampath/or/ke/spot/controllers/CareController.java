package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@Transactional
@RequestMapping("/c&t")
public class CareController {
    @Autowired
    public ProgramsService programsService;
    @Autowired
    public CountiesService countiesService;
    @Autowired
    public WardService wardService;
    @Autowired
    public FacilitiesService facilitiesService;
    @Autowired
    public SubCountiesService subCountiesService;
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            Date nowDate = new Date();

            modelAndView.addObject("counties", countiesService.getAllDataset());
            modelAndView.addObject("partner", programsService.getAllPrograms());
            modelAndView.addObject("subcounty", subCountiesService.getAllDataset());
            modelAndView.addObject("ward", wardService.getAllDataset());
            modelAndView.addObject("facility", facilitiesService.getAllDataset());

           // ProcessSurgeData

            modelAndView.setViewName("ct");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }

    @RequestMapping(value = "/surge", method = RequestMethod.GET)
    public ModelAndView surge(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            Date nowDate = new Date();

            modelAndView.setViewName("ct");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }


}
