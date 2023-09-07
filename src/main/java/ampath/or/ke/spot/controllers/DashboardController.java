package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.FacilitiesService;

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
@RequestMapping("/spot")
public class DashboardController {
      @Autowired
    private FacilitiesService facilitiesService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView overview(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            Date nowDate = new Date();
            modelAndView.addObject("today",nowDate);
            modelAndView.addObject("counties", facilitiesService.FacilitiesByCounty().size());
            modelAndView.addObject("subcounties",facilitiesService.FacilitiesBySubcounty().size());
             modelAndView.addObject("wards",facilitiesService.FacilitiesByWard().size());
              modelAndView.addObject("facilities",facilitiesService.FacilitiesByFacilityname().size());
              modelAndView.addObject("amrs",facilitiesService.CountEMRS("AMRS").size());
               modelAndView.addObject("kenyaemr",facilitiesService.CountEMRS("KenyaEMR").size());
            modelAndView.setViewName("programdash");
            return modelAndView;
        }
            else{
                return new ModelAndView("redirect:/setup");
            }
    }

    @RequestMapping(value = "/analytics", method = RequestMethod.GET)
    public ModelAndView analytics(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("dashboard");
            return modelAndView;
        }
            else{
                return new ModelAndView("redirect:/setup");
            }
        }
}
