package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.Pendullums;
import ampath.or.ke.spot.services.PendullumService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import static ampath.or.ke.spot.utils.GlobalVars._toJson;

@RestController
@RequestMapping("/rest/api/v1/pendulum")
public class PendullumAPI {
    @Autowired
    private PendullumService pendullumService;

    @GetMapping("/records")
    public List<Tuple> getRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size) {
        return pendullumService.getRecordsWithPagination(page, size);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<Tuple>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        // Calculate the starting index of the page
        int start = page * size;

        // Retrieve the entire dataset
        List<Tuple> allData = pendullumService.getAllDataset();

        // Check if the starting index is out of bounds
        if (start >= allData.size()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Calculate the ending index of the page
        int end = Math.min(start + size, allData.size());

        // Get the sublist corresponding to the current page
        List<Tuple> users = allData.subList(start, end);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="/dataset", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String HTSlist(HttpSession session) throws Exception {
        JSONArray jsonArray = new JSONArray();
        String json="";
       // if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            String output = "";
            Date nowDate = new Date();
            String pattern = "yyyy-MM-dd hh:mm:ss";
            String jssons = "";
            List<Tuple> kashaClientsList = pendullumService.getAllDataset();
            List<ObjectNode> jsonn = _toJson(kashaClientsList);
            jssons = jsonn.toString();

           /* try {
                FileWriter file = new FileWriter("F:\myjson.json");
                file.write(jsonObject3.toJSONString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            // String jstring =jssons.substring(1);
            // String fstring = "["+jstring+"]";
            // System.out.println("Client Line size "+ fstring);
            json =  jssons;
       /* }
        else {
            JsonObject jsonn = new JsonObject();
            jsonn.addProperty("error", "Authetications is Required");
            jsonArray.put(jsonn);
            json=jsonArray.toString();

        } */
        return json;
    }
}
