package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.services.FacilitiesService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/sites")
@EnableSwagger2
public class FacilitiesController {

    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;
    @Value("${spring.etl.db}")
    public String etl_db;
    @Value("${spring.ndwr.db}")
    public String ndwr_db;

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

    @RequestMapping(value = "/amrs", method = RequestMethod.GET)
    public ModelAndView refresh(HttpSession session) throws IOException, JSONException, SQLException {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            List<Facilities> facilitiesList = facilitiesService.getAllDataset();
            for (int x = 0; x < facilitiesList.size(); x++) {
                Facilities facilities = facilitiesList.get(x);
                String mflcode = facilities.getMflcode();
                Connection con = DriverManager.getConnection(server + ndwr_db, username, password);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = stmt.executeQuery("select * from mfl_codes m where m.mfl_code ='" + mflcode + "' ;");
                ArrayList<String> emr_id = new ArrayList<String>();
                while (resultSet.next()) {
                    emr_id.add(resultSet.getString("location_id"));
                   }
                String emrids = removeFirstandLast(emr_id.toString());
                System.out.println("Locations are  For mflcode " + facilities.getMflcode() + " name " + facilities.getFacilityname() + " EMR " + facilities.getEmr() + " EMRS Id " +emrids);

                Connection conn = DriverManager.getConnection(server+etl_db , username, password);
                Statement stmtt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String xx = "select case when sum(on_art_this_month) is null then 0 else sum(on_art_this_month) end   tx_curr from hiv_monthly_report_dataset_frozen where endDate='2023-09-30' and status='active' and location_id in ("+ emrids +");";
               System.out.println("Text "+ xx);
               if(emrids.length()>=1) {
                   ResultSet resultSett = stmtt.executeQuery(xx);

                   while (resultSett.next()) {
                       int xxxx=0;
                       if(resultSett.getString("tx_curr").length()>0){
                           xxxx=Integer.parseInt(resultSett.getString("tx_curr"));

                       }
                       facilities.setTxcurr(xxxx);
                   }
                   facilities.setAmrsid(emrids);

                   facilitiesService.save(facilities);
               }

            }
            List<Facilities> facilities = facilitiesService.getAllDataset();

            modelAndView.addObject("facilities",facilities);
            modelAndView.setViewName("facilities");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
        public static String removeFirstandLast(String str)
        {

            // Creating a StringBuffer object
            StringBuffer sb = new StringBuffer(str);

            // Removing the last character
            // of a string
            sb.delete(str.length() - 1, str.length());

            // Removing the first character
            // of a string
            sb.delete(0, 1);

            // Converting StringBuffer into
            // string & return modified string
            return sb.toString();
        }
}
