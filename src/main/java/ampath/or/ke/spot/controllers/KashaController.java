package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.KashaDeliveries;
import ampath.or.ke.spot.services.KashaDeliveriesService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;

//@Controller
//@Transactional
//@RequestMapping("/clients")
@Controller
@Transactional
@RequestMapping("/kasha")
public class KashaController {

    @Value("${spring.etl.username}")
    public  String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public  String server;
        @Autowired
        KashaDeliveriesService kashaDeliveriesService;

    // new encounter '987009c6-6f24-43f7-9640-c285d6553c63' formId='1447'
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<Object> view(HttpSession session) throws IOException, JSONException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, SQLException {

       List<KashaDeliveries> kashaDeliveriesList = kashaDeliveriesService.getInAMRS(0);
       String fvalues="";
       for(int x=0;x<kashaDeliveriesList.size();x++) {
           String person_id = String.valueOf(kashaDeliveriesList.get(x).getPerson_id());
           String edate = kashaDeliveriesList.get(x).getCreated_at();

           String sql = "SELECT p.uuid,  c.uuid concept_uuid, \n" +
                   "x.encounter_id  , case when o.value_coded is null then o.value_datetime else o.value_coded end output\n" +
                   " From amrs.obs o \n" +
                   " INNER JOIN amrs.concept c ON c.concept_id = o.concept_id\n" +
                   "  INNER JOIN amrs.person p on p.person_id=o.person_id\n" +
                   " INNER JOIN\n" +
                   "(SELECT  \n" +
                   "e.encounter_id \n" +
                   "FROM amrs.encounter e\n" +
                   "WHERE e.patient_id = '"+person_id +"' \n" +
                   "  AND e.encounter_type = 2 \n" +
                   "ORDER BY e.encounter_id DESC \n" +
                   " LIMIT 1) x on x.encounter_id=o.encounter_id\n" +
                   " where o.concept_id in(1265,1261,1268,1088,1109,5096,1192,6744,1255)\n";
           Connection conn = DriverManager.getConnection(server, username, password);
           Statement stmtt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           System.out.println("Text " + sql);
           ResultSet resultSett = stmtt.executeQuery(sql);
           String puuid = "";
           JSONArray jsonObservations = new JSONArray();

           String luuid = "";

           while (resultSett.next()) {
               puuid = resultSett.getString("uuid").toString();
               JSONObject jsonObservation = new JSONObject();
               //  jsonObservation.put("person",resultSett.getString("uuid").toString());//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
               jsonObservation.put("concept", resultSett.getString("concept_uuid").toString());//String.valueOf(conceptId));//+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
               jsonObservation.put("value", resultSett.getString("output").toString());
               jsonObservation.put("obsDatetime", edate);//+"T06:08:25.000+0000"
               jsonObservations.put(jsonObservation);
           }

           JSONObject jsonVisit = new JSONObject();
           jsonVisit.put("patient", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
           jsonVisit.put("visitType", "d4ac2aa5-2899-42fb-b08a-d40161815b48");
           jsonVisit.put("startDatetime", edate); //+"T06:08:25.000+0000"
           jsonVisit.put("stopDatetime", edate); //+"T06:09:25.000+0000"

           JSONArray jsonProviders = new JSONArray();
           JSONObject jsonProvider = new JSONObject();
           jsonProvider.put("provider", "XXXXXUUID");
           jsonProvider.put("encounterRole", "a0b03050-c99b-11e0-9572-0800200c9a66");
           jsonProviders.put(jsonProvider);

           JSONObject jsonEncounter = new JSONObject();
           jsonEncounter.put("encounterProviders", jsonProviders);
           jsonEncounter.put("patient", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
           jsonEncounter.put("encounterDatetime", edate); //+"T06:09:00.000+0000"
           jsonEncounter.put("encounterType", "987009c6-6f24-43f7-9640-c285d6553c63");
           jsonEncounter.put("form", "1447");
           jsonEncounter.put("location", luuid);
           jsonEncounter.put("visit", jsonVisit);
           jsonEncounter.put("obs", jsonObservations);
           fvalues = fvalues+jsonEncounter.toString();
       }

          return new ResponseEntity<Object>(fvalues, HttpStatus.OK);
    }

}
