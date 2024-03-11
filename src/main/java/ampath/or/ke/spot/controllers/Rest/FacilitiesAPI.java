package ampath.or.ke.spot.controllers.Rest;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ampath.or.ke.spot.models.Facilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.val;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ampath.or.ke.spot.services.FacilitiesService;

import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;

import static ampath.or.ke.spot.utils.GlobalVars._toJson;


//@EnableSwagger2
@RestController
@CrossOrigin
@RequestMapping("api/v1/facilities")
public class FacilitiesAPI {

    @Autowired
    public FacilitiesService facilitiesService;

    // @GetMapping("/getall",produces = "application/json")
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String EMRlist(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<?> emrs = facilitiesService.EMRDistribution();

            String jssons = "";
            for (int x = 0; x < emrs.size(); x++) {
                JsonObject json = new JsonObject();
                String emr = Array.get(emrs.get(x), 0).toString();
                int cunt = Integer.parseInt(Array.get(emrs.get(x), 1).toString());

                json.addProperty("value", cunt);
                json.addProperty("name", emr);

                jsonArray.put(json);

            }
        } else {
            JsonObject json = new JsonObject();
            json.addProperty("error", "Authetications is Required");
            jsonArray.put(json);

        }


        return jsonArray.toString();
    }

    @RequestMapping(value = "/gettx_curr", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String ARTDistribution(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<?> emrs = facilitiesService.ARTDistribution();

            String jssons = "";
            for (int x = 0; x < emrs.size(); x++) {
                JsonObject json = new JsonObject();
                String county = Array.get(emrs.get(x), 0).toString();
                int cunt = Integer.parseInt(Array.get(emrs.get(x), 1).toString());

                json.addProperty("value", cunt);
                json.addProperty("name", county);

                jsonArray.put(json);

            }
        } else {
            JsonObject json = new JsonObject();
            json.addProperty("error", "Authetications is Required");
            jsonArray.put(json);

        }


        return jsonArray.toString();
    }

    @RequestMapping(value = "/tx_curr", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String AllTx_Curr(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<?> emrs = facilitiesService.ARTDistribution();

            String jssons = "";
            for (int x = 0; x < emrs.size(); x++) {
                JsonObject json = new JsonObject();
                String county = Array.get(emrs.get(x), 0).toString();
                int cunt = Integer.parseInt(Array.get(emrs.get(x), 1).toString());

                // json.addProperty("value", cunt);
                json.addProperty("name", county);
                json.addProperty("y", cunt);
                json.addProperty("drilldown", county);

                jsonArray.put(json);

            }
        } else {
            JsonObject json = new JsonObject();
            json.addProperty("error", "Authetications is Required");
            jsonArray.put(json);

        }


        return jsonArray.toString();
    }

    @RequestMapping(value = "/countines/{partner}", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String counties(HttpSession session, @PathVariable List<String> partner) throws Exception {
        JSONArray jsonArray = new JSONArray();
        String json="";
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<Tuple> counties = facilitiesService.CountiesPerPartner(partner);
            List<ObjectNode> jsonn = _toJson(counties);
            json = jsonn.toString();
           /* for(Object object:counties)
            {
                JsonObject jsonn = new JsonObject();
               // jsonn.addProperty("county", (String) object);
                jsonn.addProperty("name", (String) object);
                jsonn.addProperty("value", (String) object);
                jsonArray.put(jsonn);
                System.out.println(object);
            }
            */
          //  json = jsonArray.toString();

        } else {
            JsonObject jsonn = new JsonObject();
            jsonn.addProperty("error", "Authetications is Required");
            jsonArray.put(jsonn);
            json=jsonArray.toString();

        }
        return json;

    }
}

