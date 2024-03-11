package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.scheduling.ScheduledTasks;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import ampath.or.ke.spot.services.KashaClientsServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static ampath.or.ke.spot.scheduling.ScheduledTasks.*;

@RestController
@RequestMapping("/rest/v1/api/kasha")
public class KashaAPI {
    @Autowired
    private KashaClientsServices kashaClientsServices;
    @ResponseBody
    @RequestMapping(value="/clients/{concestedDate}", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String Kashalist(@PathVariable String concestedDate) throws Exception {
        // ScheduledTasks.KashaClients();
       // http://localhost:8081/rest/v1/api/kasha/clients/2024-02-13
      //  String output = "";
      //  Date nowDate = new Date();
      //  String pattern = "yyyy-MM-dd hh:mm:ss";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date concested_Date = formatter.parse(concestedDate);

        List<KashaClients> kashaClientsList =  kashaClientsServices.findByDateConsentedGreaterThanEqual(concested_Date);
        String jssons="";
        String jstring="";
        if (kashaClientsList.size()>0) {
            for (int x = 0; x < kashaClientsList.size(); x++) {
                KashaClients att = kashaClientsList.get(x);
                Gson gson = new Gson();
                String jsons = gson.toJson(att);//att);
                jssons = jssons + "," + jsons;
                 jstring =jssons.substring(1);
            }
        }
        else{
            jstring="Not Data available";
        }

        String fstring = "["+jstring+"]";
      //  System.out.println("Client Line size "+ fstring);
        return fstring;
    }
    @ResponseBody
    @RequestMapping(value="/clients", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String HTSlist() throws Exception {
       // ScheduledTasks.KashaClients();
        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        List<KashaClients> kashaClientsList =  kashaClientsServices.getAllDataset();
        String jssons="";
        for (int x=0;x<kashaClientsList.size();x++) {
            KashaClients att = kashaClientsList.get(x);
            Gson gson = new Gson();
            String jsons = gson.toJson(att);//att);
            jssons =  jssons+","+jsons;

        }
        String jstring =jssons.substring(1);
        String fstring = "["+jstring+"]";
      //  System.out.println("Client Line size "+ fstring);
        return fstring;
    }
    @ResponseBody
    @RequestMapping(value="/clinical", method = RequestMethod.POST,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> handlePostRequest(@RequestBody KashaDelivery requestData) {

        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
       /* List<KashaClients> kashaClientsList =  kashaClientsServices.getAllDataset();
        String jssons="";
        for (int x=0;x<kashaClientsList.size();x++) {
            KashaClients att = kashaClientsList.get(x);
            Gson gson = new Gson();
            String jsons = gson.toJson(att);//att);
            jssons =  jssons+","+jsons;

        }
        */
       // String jstring =jssons.substring(1);
       // String fstring = "["+jstring+"]";
       // System.out.println("Client Line size "+ fstring);
        return ResponseEntity.ok("Request received successfully");
    }
}

