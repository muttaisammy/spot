package ampath.or.ke.spot.controllers;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/db_manager")
public class DatabaseController {

    @RequestMapping(value = "/masterdatabases", method = RequestMethod.GET)
    public ModelAndView masterdatabase(HttpSession session) throws IOException, JSONException {
        System.out.println("Imefika Hapa");
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
          /*  List<Facilities> facilitiesList = facilitiesService.searchByFtypeLike("F");
            List<Facilities> contiesList = facilitiesService.searchByFtypeLike("C");
            List<DatabaseInfo> databaseInfos = databaseinfoService.getAllDataset();*/

          //  modelAndView.addObject("dbs", databaseInfos);
         //   modelAndView.addObject("facilities", facilitiesService.searchByFtypeLike("F"));
         //   modelAndView.addObject("counties", facilitiesService.searchByFtypeLike("C"));
         //   modelAndView.addObject("countfacilities", facilitiesList.size());
         //   modelAndView.addObject("countcounties", contiesList.size());
            modelAndView.setViewName("masterdatabase");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }
}
