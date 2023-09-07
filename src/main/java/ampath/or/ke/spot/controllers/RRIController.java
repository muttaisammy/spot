package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.models.RRI;
import ampath.or.ke.spot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/rri")
public class RRIController {
    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;
    @Value("${app.dir}")
    public String data_path;

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
    public RRIService rriService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView postss(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            List<County> countyList = countiesService.getAllDataset();
            List<RRI> rriList = rriService.getAllDataset();
            modelAndView.addObject("counties", countyList);
            modelAndView.addObject("partner", programsService.getAllPrograms());
            modelAndView.addObject("subcounty", subCountiesService.getAllDataset());
            modelAndView.addObject("ward", wardService.getAllDataset());
            modelAndView.addObject("facility", facilitiesService.getAllDataset());

            modelAndView.setViewName("hts_dash");
            return modelAndView;
        }
        else{
            return new ModelAndView("redirect:/setup");
        }
    }
}
