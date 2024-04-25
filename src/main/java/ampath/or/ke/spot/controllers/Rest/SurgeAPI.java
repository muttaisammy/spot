package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.SurgeReport;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import ampath.or.ke.spot.services.SurgeService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import static ampath.or.ke.spot.utils.GlobalVars._toJson;

@RestController
@RequestMapping("/rest/v1/api/surge")
public class SurgeAPI {
    @Autowired
    private SurgeService surgeService;
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String EMRlist(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<?> emrs = surgeService.getAllData();
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

    @RequestMapping(value = "/old_summaries", method = RequestMethod.GET, produces = "application/json")
//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String Summaries(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        String json="";
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<?> surgeReportList = surgeService.getSummaries();
            Gson gson = new Gson();
            String jsonArrays = gson.toJson(surgeReportList);
            json =jsonArrays;
           // jsonArray.put(surgeReportList.toString());


        } else {
            JsonObject jsonn = new JsonObject();
            jsonn.addProperty("error", "Authetications is Required");
            jsonArray.put(json);
            json = jsonArray.toString();

        }


        return json;
    }
    @RequestMapping(value = "/summaries", method = RequestMethod.GET, produces = "application/json")
    public String jsonSummaries(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        String json="";
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            List<Tuple> surgeReportList = surgeService.getallSummaries();
            List<ObjectNode> jsonn = _toJson(surgeReportList);
           // Gson gson = new Gson();
           // String jsonArrays = gson.toJson(surgeReportList);
            json = jsonn.toString();
            // jsonArray.put(surgeReportList.toString());


        } else {
            JsonObject jsonn = new JsonObject();
            jsonn.addProperty("error", "Authetications is Required");
            jsonArray.put(json);
            json = jsonArray.toString();

        }


        return json;
    }
}
