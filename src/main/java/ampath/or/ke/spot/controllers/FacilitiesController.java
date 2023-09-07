package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.services.FacilitiesService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/sites")
@EnableSwagger2
public class FacilitiesController {
    @Autowired
    public FacilitiesService facilitiesService;


    @RequestMapping(value = "/linelist", method = RequestMethod.GET)
    public ModelAndView facilities(HttpSession session) throws IOException, JSONException {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
           // List<Facilities> facilitiesList = facilitiesService.searchByFtypeLike("F");
           // List<Facilities> contiesList = facilitiesService.searchByFtypeLike("C");
          /*  modelAndView.addObject("facilities", facilitiesService.searchByFtypeLike("F"));
            modelAndView.addObject("counties", facilitiesService.searchByFtypeLike("C"));
            modelAndView.addObject("countfacilities", facilitiesList.size());
            modelAndView.addObject("countcounties", contiesList.size()); */
            modelAndView.setViewName("masterfacilities");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }
}
