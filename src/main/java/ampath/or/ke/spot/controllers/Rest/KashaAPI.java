package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.models.KashaDrugs;
import ampath.or.ke.spot.scheduling.ScheduledTasks;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import ampath.or.ke.spot.services.KashaClientsServices;
import ampath.or.ke.spot.services.KashaDrugService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static ampath.or.ke.spot.scheduling.ScheduledTasks.*;

@RestController
@RequestMapping("/rest/v1/api/kasha")
public class KashaAPI {
    @Autowired
    private KashaClientsServices kashaClientsServices;
    @Autowired
    private KashaDrugService kashaDrugService;
    private final ObjectMapper objectMapper;
    @Autowired
    public KashaAPI(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
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

        List<KashaClients> kashaClientsList =  kashaClientsServices.findByDateConsentedGreaterThanEqual(1,concested_Date);
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
        List<KashaClients> kashaClientsList =  kashaClientsServices.getAllEligible(1);
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
    @RequestMapping(value="/clinical/{concestedDate}", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> handlePostRequest(@PathVariable String concestedDate) throws ParseException, JSONException {

        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date concested_Date = formatter.parse(concestedDate);
        List<JSONObject> clients_entities = new ArrayList<JSONObject>();
        List<KashaClients> kashaClientsList =  kashaClientsServices.findByDateConsentedGreaterThanEqual(1,concested_Date);

        if(kashaClientsList.size()>0){
            for(int x =0;x<kashaClientsList.size();x++) {
                JSONObject client = new JSONObject();
                client.put("ccc", kashaClientsList.get(x).getIdentifier());
                client.put("first_name", kashaClientsList.get(x).getFirst_name());
                client.put("last_name", kashaClientsList.get(x).getLast_name());
                client.put("phone_number", kashaClientsList.get(x).getPhone_number());
                client.put("secondary_phone_number", kashaClientsList.get(x).getSecondary_phone_number());
                client.put("address", kashaClientsList.get(x).getAddress());
                client.put("estate_village", kashaClientsList.get(x).getEstate_village());
                client.put("county", kashaClientsList.get(x).getCounty());
                client.put("expected_next_delivery_date", kashaClientsList.get(x).getExpected_next_delivery_date());
                client.put("nearest_landmark", kashaClientsList.get(x).getNearest_landmark());
                client.put("gender", kashaClientsList.get(x).getGender());
                client.put("age", kashaClientsList.get(x).getAge());
                List<KashaDrugs> drugsList = kashaDrugService.findByPatientId(String.valueOf(kashaClientsList.get(x).getPerson_id()));
                JSONObject drugs = new JSONObject();
               if(drugsList.size()>0){
                   for(int y=0;y<drugsList.size();y++){
                       drugs.put("medication_pick_up_date",drugsList.get(y).getMedicalPickUpTCA());
                       drugs.put("regimen",drugsList.get(y).getDrug());
                       drugs.put("medication_type",drugsList.get(y).getMedicationType());
                       drugs.put("clinical_TCA",drugsList.get(y).getClinicalDate());
                       drugs.put("qty",drugsList.get(y).getQty());
                       drugs.put("prediction_score",drugsList.get(y).getPredictionScores());
                   }
               }
                client.put("medication", drugs);
                clients_entities.add(client);
            }
        }
        return new ResponseEntity<Object>(clients_entities, HttpStatus.OK);
        //return ResponseEntity.ok("Request received successfully");
    }
}

