package ampath.or.ke.spot.scheduling;

import ampath.or.ke.spot.models.AfyastatErrors;
import ampath.or.ke.spot.services.AfyastatErrorsService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

@Component
public class ScheduledTasks {
    @Value("${spring.afyastat.username}")
    public String username;
    @Value("${spring.afyastat.password}")
    public String password;
    @Value("${spring.afyastat.server}")
    public String server;
    @Value("${spring.openmrs.url}")
    private String url;
    @Value("${spring.openmrs.remoteurl}")
    private String urremoteurll;
    @Value("${spring.openmrs.username}")
    private String openmrs_username;
    @Value("${spring.openmrs.password}")
    private String openmrs_password;

    @Autowired
    private AfyastatErrorsService afyastatErrorsService;

    @Scheduled(cron = "0 */1 * ? * *")
    public void ProcessErrors() throws JSONException, ParseException, SQLException, IOException {
      // HttpSession session= new HttpSession()
        List<AfyastatErrors> afyastatErrorsList = afyastatErrorsService.getAllErrors();
        Connection con = DriverManager.getConnection(server, username, password);

        for(int x=0;x<afyastatErrorsList.size();x++){

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            int id = afyastatErrorsList.get(x).getEid();
            ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + id + "");
            rs.last();
            x = rs.getRow();

            rs.beforeFirst();
            while (rs.next()) {
              String  luuid = rs.getString(1).toString();
            }

        }
        con.close();



    }


   // @Scheduled(cron = "0 */1 * ? * *")
    /*public void ProcessErrors() throws JSONException, ParseException, SQLException, IOException {
      //  HttpSession session= new HttpSession()
        String originalInput = openmrs_username+":"+openmrs_password;
        String authcode = Base64.getEncoder().encodeToString(originalInput.getBytes());

        OkHttpClient sessionclient = new OkHttpClient();
        Request sessionrequest = new Request.Builder()
                .url( url+"/session")
                .method("GET", null)
                .addHeader("Authorization", "Basic " + authcode)
                .build();
        Response sessionresponse = sessionclient.newCall(sessionrequest).execute();

        String json = sessionresponse.body().string();
        System.out.println("Session "+json);
        JSONObject jsonObject = new JSONObject(json);
        String sessionID = (String) jsonObject.get("sessionId");
        String cookie="JSESSIONID="+sessionID;
       // session.setAttribute("cookie", cookie);

        String discripter = "";
       // List<AfyastatErrors> afyastatErrorsList = afyastatErrorsService.getByDiscriminatorAndResponsecode("json-encounter", "500");
        List<AfyastatErrors> afyastatErrorsList = afyastatErrorsService.getErrorsnullcode("json-encounter");

        int xxx = afyastatErrorsList.size();

        for (int y = 0; y < xxx; y++) {

            AfyastatErrors afyastatErrors = afyastatErrorsList.get(y);
            JSONObject jsonPayload = new JSONObject(afyastatErrors.getPayload());
             originalInput = openmrs_username + ":" + openmrs_password;
             authcode = Base64.getEncoder().encodeToString(originalInput.getBytes());

            if (afyastatErrors.getDiscriminator().equals("json-encounter")) {
                String xx = jsonPayload.getJSONObject("observation").toString();
                System.out.println(" Payload " + xx);
                JSONArray jsonObservations = new JSONArray();
                String edate = "";
                String dtime = jsonPayload.getJSONObject("encounter").getString("encounter.encounter_datetime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                edate = sdf2.format(sdf.parse(dtime));

                JSONObject jsonObs = new JSONObject(jsonPayload.getJSONObject("observation").toString());
                JSONObject obsJsonObject = (JSONObject) jsonObs;
                for (Iterator it = obsJsonObject.keys(); it.hasNext(); ) {
                    String conceptQuestion = String.valueOf(it.next());
                    Object valueObject = obsJsonObject.get(conceptQuestion);
                    String rquotes = conceptQuestion.substring(1, conceptQuestion.length() - 1);
                    String ovalues = valueObject.toString();
                    String occupationConAns = handleEditObsValues(rquotes.replace("^", "_"));
                    String[] ObsQuestion = occupationConAns.split("_");
                    String values = "";
                    //  String puuid = jsonPayload.getJSONObject("patient").getString("patient.uuid");
                    String cuuid = jsonPayload.getJSONObject("patient").getString("patient.uuid");
                    String puuid = getpersonuuid(cuuid);
                    String obvaluesAns = ovalues.replace("^", "_");
                    String[] ObsValues = obvaluesAns.split("_");

                    if (ObsValues.length > 1) {
                        values = ObsValues[1].toString();
                    } else {
                        values = ObsValues[0].toString();
                    }
                    if (ObsQuestion.length > 0) {
                        try {
                            int conceptId = Integer.parseInt(ObsQuestion[0]);
                            if (obvaluesAns.contains("{")) {
                                //Obs Set
                                // System.out.println("Parentkey delimiter : "+ conceptId +" Parentvalue { } "+valueObject.toString() +"size "+ObsValues.length+" obvaluesAns "+obvaluesAns +" fvalues iko na { } "+values ); //+" value "+ObsValues[1].toString()
                                JSONObject jsonObsSet = new JSONObject(obvaluesAns);
                                String parentuuid = "";
                                JSONArray jsonchildObservation = new JSONArray();


                                for (Iterator itset = jsonObsSet.keys(); itset.hasNext(); ) {
                                    String conceptSetQuestion = String.valueOf(itset.next());
                                    // System.out.println("Set concempt "+ conceptSetQuestion);
                                    Object valueSetObject = jsonObsSet.get(conceptSetQuestion);
                                    String rsetquotes = conceptSetQuestion.substring(1, conceptSetQuestion.length() - 1);
                                    String obsSetConAns = handleEditObsValues(rsetquotes.replace("^", "_"));
                                    String[] ObsSetQuestion = obsSetConAns.split("_");
                                    String obsetvaluesAns = valueSetObject.toString();
                                    String[] ObsSetValues = obsetvaluesAns.split("_");
                                    String setvalues = "";
                                    if (ObsSetValues.length > 1) {
                                        setvalues = ObsSetValues[1].toString();
                                    } else {
                                        setvalues = ObsSetValues[0].toString();
                                    }
                                    if (ObsSetQuestion.length > 0) {
                                        int conceptsetId = Integer.parseInt(ObsSetQuestion[0]);
                                        String conceptuuid = getconceptuuid(String.valueOf(conceptsetId))[0];
                                        String dtype = getconceptuuid(String.valueOf(conceptsetId))[1];
                                        parentuuid = getconceptuuid(String.valueOf(conceptId))[0];
                                        //    System.out.println("SetParentkey delimiter : "+ conceptsetId +" SetParentvalue "+valueSetObject.toString() +" Setsize "+ObsSetValues.length+" SetobvaluesAns "+obsetvaluesAns +" Setfvalues "+setvalues );
                                        System.out.println("Parent concept " + conceptId + "SetParentkey delimiter : " + conceptsetId + " Setfvalues " + setvalues + " dtype " + dtype);

                                        String value = "";
                                        if (dtype.equals("2")) {
                                            value = getconceptuuid(setvalues)[0];
                                        } else if (dtype.equals("3") && values.equals("")) {
                                            value = "None";
                                        } else if (dtype.equals("1") && values.contains("")) {
                                            value = "0";
                                        }
                                        else {
                                            value = setvalues;
                                        }
                                        JSONObject jsonObservation = new JSONObject();
                                        jsonObservation.put("person", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
                                        jsonObservation.put("concept", conceptuuid);///String.valueOf(conceptsetId));
                                        jsonObservation.put("value", value);
                                        jsonObservation.put("obsDatetime", edate);
                                        jsonchildObservation.put(jsonObservation);

                                    }
                                }
                                // Add ObsGroping here
                                JSONObject jsonObservation = new JSONObject();
                                // jsonObservation.put("person","60168b73-60f1-4044-9dc6-84fdcbc1962c");
                                jsonObservation.put("concept", parentuuid);///String.valueOf(conceptsetId));
                                jsonObservation.put("groupMembers", jsonchildObservation);
                                jsonObservations.put(jsonObservation);

                                //End of Obs Set
                            } else {
                                //System.out.println("Parentkey delimiter : "+ conceptId +" Parentvalue "+valueObject.toString() +" size "+ObsValues.length+" obvaluesAns "+obvaluesAns +" fvalues "+values );
                                //    select uuid from concept where concept_id= 11838
                                String conceptuuid = getconceptuuid(String.valueOf(conceptId))[0];
                                String dtype = getconceptuuid(String.valueOf(conceptId))[1];
                                System.out.println("Parentkey delimiter : " + conceptId + " fvalues " + values + " dtype " + dtype +" Provider "+ afyastatErrors.getProvider());

                                String value = "";
                                if (dtype.equals("2")) {
                                    value = getconceptuuid(values)[0];
                                } else if (dtype.equals("3") && values.equals("")) {
                                    value = "None";
                                } else if (conceptuuid.equals("64807fb6-0ca3-4def-a62a-2d5f66c4328a") && values.contains("")) {
                                    value = edate;
                                }
                                else if (dtype.equals("1") && values.contains("")) {
                                    value = "0";
                                }
                                else {
                                    value = values;
                                }
                                JSONObject jsonObservation = new JSONObject();
                                jsonObservation.put("person", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
                                jsonObservation.put("concept", conceptuuid);//String.valueOf(conceptId));//+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                                jsonObservation.put("value", value);
                                jsonObservation.put("obsDatetime", edate);//+"T06:08:25.000+0000"
                                jsonObservations.put(jsonObservation);
                            }
                        } catch (Exception ex) {
                            // System.out.println("key delimiter NaN : "+ ex.getMessage() +" kdkdkdk "+ObsQuestion[0]);
                        }
                    } else {
                        //int conceptId = Integer.parseInt(ObsQuestion[0]);
                        //System.out.println("key delimiter : "+ conceptId );
                    }
                }
                //Encounter Details
                String euuid = jsonPayload.getJSONObject("encounter").getString("encounter.setup_config_uuid");
                String location = jsonPayload.getJSONObject("encounter").getString("encounter.location_id");
                String provider = jsonPayload.getJSONObject("encounter").getString("encounter.provider_id_select");
                String form = jsonPayload.getJSONObject("encounter").getString("encounter.form_uuid");
                String cuuid = jsonPayload.getJSONObject("patient").getString("patient.uuid");
                String puuid = getpersonuuid(cuuid);
                Connection con = DriverManager.getConnection(server, username, password);
                String luuid = "";
                String pruuid = "";

                System.out.println("Provider details "+provider);

                int x = 0;
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + location + "");
                rs.last();
                x = rs.getRow();

                rs.beforeFirst();
                while (rs.next()) {
                    luuid = rs.getString(1).toString();
                }

                ResultSet prs = stmt.executeQuery("select uuid from afyastat.provider where identifier='" + provider + "'");
                prs.last();
                prs.beforeFirst();
                while (prs.next()) {
                    pruuid = prs.getString(1).toString();
                }

                System.out.println("Provider uuid is "+ pruuid);

                con.close();

                JSONObject jsonVisit = new JSONObject();
                jsonVisit.put("patient", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
                jsonVisit.put("visitType", "9865c08a-97a4-4571-b873-dd422583b3a7");
                jsonVisit.put("startDatetime", edate); //+"T06:08:25.000+0000"
                jsonVisit.put("stopDatetime", edate); //+"T06:09:25.000+0000"


                JSONArray jsonProviders = new JSONArray();
                JSONObject jsonProvider = new JSONObject();
                jsonProvider.put("provider", pruuid);
                jsonProvider.put("encounterRole", "a0b03050-c99b-11e0-9572-0800200c9a66");
                jsonProviders.put(jsonProvider);

                JSONObject jsonEncounter = new JSONObject();
                jsonEncounter.put("encounterProviders", jsonProviders);
                jsonEncounter.put("patient", puuid);//"60168b73-60f1-4044-9dc6-84fdcbc1962c");
                jsonEncounter.put("encounterDatetime", edate); //+"T06:09:00.000+0000"
                jsonEncounter.put("encounterType", euuid);
                jsonEncounter.put("form", form);
                jsonEncounter.put("location", luuid);
                jsonEncounter.put("visit", jsonVisit);
                jsonEncounter.put("obs", jsonObservations);

                // System.out.println("Patient "+ puuid+" Encounter uuid "+euuid+" Etime "+ edate+" location "+luuid+" provider "+pruuid+" form "+form);
                System.out.println("Index " + y + " Of " + xxx + " Payload " + jsonEncounter.toString());

                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonEncounter.toString());
                Request request = new Request.Builder()
                        .url(urremoteurll + "encounter")
                        .method("POST", body)
                        .addHeader("Authorization", "Basic " + authcode)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Cookie", cookie)//(String) session.getAttribute("cookie"))
                        .build();
                Response response = client.newCall(request).execute();
                String bb = response.body().string();
                int code = response.code();
                String status = "";
                System.out.println("Response Code Imesave " + code);
                if (code == 400) {
                    status = "Patient Exists";
                } else {
                    status = "Processed Successfully";
                }
                afyastatErrors.setStatus(status);
                afyastatErrors.setResponsecode(String.valueOf(code));
                afyastatErrorsService.save(afyastatErrors);

                System.out.println(bb);

            }


        }

    }

*/
    ////////////--------------


    public String[] getconceptuuid(String conceptId) throws SQLException {
        String conceptuuid = "";
        String dtype = "";
        Connection con = DriverManager.getConnection(server, username, password);
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rss = stmt.executeQuery("select c.concept_id,c.datatype_id,c.uuid,cd.name from afyastat.concept c\n" +
                "inner join afyastat.concept_datatype cd on c.datatype_id=cd.concept_datatype_id \n" +
                " where c.concept_id in (" + conceptId + ");");
        rss.last();
        rss.beforeFirst();
        while (rss.next()) {
            conceptuuid = rss.getString(3).toString();
            dtype = rss.getString(2).toString();
        }
        if (conceptuuid.equals("")) {

            ResultSet rs = stmt.executeQuery("select c.concept_id,c.datatype_id,c.uuid,cr.code,cd.name from afyastat.concept c \n" +
                    "inner join afyastat.concept_reference_map cm  on c.concept_id=cm.concept_id\n" +
                    "inner join afyastat.concept_reference_term cr on cr.concept_reference_term_id=cm.concept_reference_term_id\n" +
                    "inner join afyastat.concept_datatype cd on c.datatype_id=cd.concept_datatype_id \n" +
                    "where cr.code in (" + conceptId + "); ");
            rs.last();
            rs.beforeFirst();
            while (rs.next()) {
                conceptuuid = rs.getString(3).toString();
                dtype = rs.getString(2).toString();
            }
        }
        con.close();

        return new String[]{conceptuuid, dtype};
    }

    public String getpersonuuid(String chtuuid) throws SQLException {
        String puuid = "";

        Connection con = DriverManager.getConnection(server, username, password);
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rss = stmt.executeQuery("select p.uuid from afyastat.patient_identifier pii\n" +
                "    inner join afyastat.person p on pii.patient_id=p.person_id\n" +
                "    where pii.identifier='" + chtuuid + "';");
        rss.last();
        rss.beforeFirst();
        while (rss.next()) {
            puuid = rss.getString(1).toString();
        }
        con.close();
        return puuid;
    }

    private String handleEditObsValues(String obsValue) {

        ArrayNode arrNodeValues = JsonNodeFactory.instance.arrayNode();
        String conceptValue = null;
        if (obsValue != null && !obsValue.equalsIgnoreCase("")) {
            for (String s : obsValue.split("_")) {
                arrNodeValues.add(s);
            }
            if (arrNodeValues != null) {
                conceptValue = (arrNodeValues.get(0).textValue());
            }
        }
        return conceptValue;
    }
}
