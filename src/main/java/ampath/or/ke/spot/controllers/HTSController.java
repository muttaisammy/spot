package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Transactional
@RequestMapping("/hts")
public class HTSController {

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
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView postss(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            List<County> countyList = countiesService.getAllDataset();
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
    @RequestMapping(value = "/tst", method = RequestMethod.GET)
    public String tst(HttpSession session) throws IOException, SQLException {
       Connection con = DriverManager.getConnection(server, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery("select person_id,date(hiv_start_date) hiv_start_date from etl.flat_hiv_summary_v15b \n" +
                    "where hiv_start_date>='2023-01-01'\n" +
                    " order by encounter_datetime desc");
            ResultSetMetaData md = resultSet.getMetaData();
            int numCols = md.getColumnCount();
            List<String> colNames = IntStream.range(0, numCols)
                    .mapToObj(i -> {
                        try {
                            return md.getColumnName(i + 1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return "?";
                        }
                    })
                    .collect(Collectors.toList());

            JSONArray result = new JSONArray();
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                colNames.forEach(cn -> {
                    try {
                        row.put(cn, resultSet.getObject(cn));
                    } catch (JSONException | SQLException e) {
                        e.printStackTrace();
                    }
                });
                result.put(row);
            }

            System.out.println(result);
            try {
                List<String> resultList = new ArrayList<String>();
                FileWriter file = new FileWriter(data_path+"artstart.json");
                file.write(result.toString());
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       return "imesiahs";
    }
    @RequestMapping(value = "/hts_tst", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String hts_tst(@RequestParam("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date startdate,
                          @RequestParam("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date enddate) throws Exception {
        String data = readFileAsString(data_path+"artstart.json");
      //  System.out.println(data);
        JSONArray jsonArray = new JSONArray(data);
        java.util.Date nowDate = new java.util.Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";

        JSONArray filteredObjects =  new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println("Element " + i + ": " + jsonArray.getJSONObject(i));
            JSONObject object = jsonArray.getJSONObject(i);
            String dd  = object.getString("hiv_start_date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(dd);

           if(date.after(startdate) && date.before(enddate)){
                filteredObjects.put(object);
            }
            //if(object.hiv_start_date)
            System.out.println(object + " " + date);
        }
        String ndata = filteredObjects.toString();
        //http://localhost/hts/hts_tst?startdate=2023-07-1&enddate=2023-07-3

        return ndata;

    }
    public static String readFileAsString(String fileName)
            throws Exception
    {
        String data = "";
        data = new String(
                Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    @RequestMapping(value = "/rri", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String RRIlist() throws Exception {
            String output = "";
            Date nowDate = new Date();
            String pattern = "yyyy-MM-dd hh:mm:ss";
            String data = readFileAsString(data_path + "hts_rri.json");
            //  System.out.println(data);
            JSONArray jsonArray = new JSONArray(data);

            JSONArray TargetObjects = new JSONArray();
            JSONArray PerformanceObjects = new JSONArray();

            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println("Element " + i + ": " + jsonArray.getJSONObject(i));
                JSONObject object = jsonArray.getJSONObject(i);
                String category = object.getString("Data Type");
                if(category.equals("Performance")){
                    PerformanceObjects.put(object);
                }else{
                    TargetObjects.put(object);
                }
            }
            //http://localhost/hts/hts_tst?startdate=2023-07-1&enddate=2023-07-3

            return PerformanceObjects.toString() ;
        }

}

