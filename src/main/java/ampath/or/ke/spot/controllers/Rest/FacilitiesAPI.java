package ampath.or.ke.spot.controllers.Rest;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ampath.or.ke.spot.services.FacilitiesService;

@RestController
@RequestMapping("/rest/v1/api/")
public class FacilitiesAPI {

 @Autowired
    public FacilitiesService facilitiesService;

    /*@ResponseBody
    @RequestMapping(value = "facilities", method = RequestMethod.GET);
    public String EMRlist() throws Exception {
        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        List<Object>  emrs  = facilitiesService.CountEMRS(pattern);

        //  System.out.println(data);
        JSONArray jsonArray = new JSONArray(emrs);

        JSONArray filteredObjects =  new JSONArray();
        return String xx="0";
    }
    */
    
}
