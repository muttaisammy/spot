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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping("/spot")
public class DashboardController {
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
    @Autowired
    public PeriodServices periodServices;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            Date nowDate = new Date();

            modelAndView.setViewName("home");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }
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
            modelAndView.addObject("onart",facilitiesService.ARTDistribution());
            modelAndView.addObject("total_onart",facilitiesService.TotalARTDistribution());

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
    @RequestMapping(value = "/RRI_dashboard", method = RequestMethod.GET)
    public ModelAndView rri(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("counties", countiesService.getAllDataset());
            modelAndView.addObject("facility", facilitiesService.getAllDataset());
            modelAndView.addObject("partner", programsService.getAllPrograms());
            modelAndView.addObject("subcounty", subCountiesService.getAllDataset());
            modelAndView.addObject("period", periodServices.getCurrentPeriod());
            modelAndView.addObject("ward", wardService.getAllDataset());
            modelAndView.setViewName("rri");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }
}
