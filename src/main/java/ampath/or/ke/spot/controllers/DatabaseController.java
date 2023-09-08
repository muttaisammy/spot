package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.DatabasesInfo;
import ampath.or.ke.spot.services.DatabasesInfoServices;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    DatabasesInfoServices databasesInfoServices;

    @RequestMapping(value = "/master", method = RequestMethod.GET)
    public ModelAndView masterdatabase(HttpSession session) throws IOException, JSONException {
        System.out.println("Imefika Hapa");
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            List<DatabasesInfo> databasesInfos = databasesInfoServices.getAllDataset();

          /*  List<Facilities> facilitiesList = facilitiesService.searchByFtypeLike("F");
            List<Facilities> contiesList = facilitiesService.searchByFtypeLike("C");
            List<DatabaseInfo> databaseInfos = databaseinfoService.getAllDataset();*/

          //  modelAndView.addObject("dbs", databaseInfos);
         //   modelAndView.addObject("facilities", facilitiesService.searchByFtypeLike("F"));
         //   modelAndView.addObject("counties", facilitiesService.searchByFtypeLike("C"));
         //   modelAndView.addObject("countfacilities", facilitiesList.size());
            modelAndView.addObject("dbs", databasesInfos);
            modelAndView.setViewName("databases");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }
}
