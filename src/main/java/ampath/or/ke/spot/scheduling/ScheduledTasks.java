package ampath.or.ke.spot.scheduling;

import ampath.or.ke.spot.models.*;
import ampath.or.ke.spot.services.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class ScheduledTasks {
    @Value("${spring.afyastat.username}")
    public String username;
    @Value("${spring.afyastat.password}")
    public String password;
    @Value("${spring.afyastat.server}")
    public String server;
    @Value("${kasha.key}")
    public String kashaKey;

    @Value("${kasha.url}")
    public String kashaURL;

    //ETL
    @Value("${spring.etl.username}")
    public String etl_username;
    @Value("${spring.etl.password}")
    public String etl_password;
    @Value("${spring.etl.server}")
    public String etl_server;
    //End of ETL
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
    @Autowired
    private KashaClientsServices kashaClientsServices;
    @Autowired
    private KashaDrugService kashaDrugService;

    @Autowired
    private KashaDeliveriesService kashaDeliveriesService;
    @Autowired
    private PendulumDataService pendulumDataService;


    //@Scheduled(cron = "0 */1 * ? * *")
    public void ProcessErrors() throws JSONException, ParseException, SQLException, IOException {
        // HttpSession session= new HttpSession()
        List<AfyastatErrors> afyastatErrorsList = afyastatErrorsService.getAllErrors();
        Connection con = DriverManager.getConnection(server, username, password);

        for (int x = 0; x < afyastatErrorsList.size(); x++) {

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int id = afyastatErrorsList.get(x).getEid();
            ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + id + "");
            rs.last();
            x = rs.getRow();

            rs.beforeFirst();
            while (rs.next()) {
                String luuid = rs.getString(1).toString();
            }

        }
        con.close();


    }

    @Scheduled(cron = "0 */59 * ? * *")
    public void ProcessKashaDeliveries() throws JSONException, ParseException, SQLException, IOException {

        // Sending get request
        URL url = new URL(kashaURL);
        Date nowDate = new Date();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+kashaKey);
        //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5jb206OjE6OjkwIiwiZXhwIjoxNTM3MzQyNTIxLCJpYXQiOjE1MzY3Mzc3MjF9.O33zP2l_0eDNfcqSQz29jUGJC-_THYsXllrmkFnk85dNRbAw66dyEKBP5dVcFUuNTA8zhA83kk3Y41_qZYx43T

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray dataArray = jsonObject.getJSONArray("data");
        int x =  dataArray.length();
        System.out.println("Total size ndo hii "+x);
        for(int y =0;y<x;y++) {
            JSONObject dataEntry = dataArray.getJSONObject(y);
            int id = dataEntry.getInt("id");
            int personId = dataEntry.getInt("person_id");
            String identifier = dataEntry.getString("identifier");
            String first_name = dataEntry.getString("first_name");
            String last_name = dataEntry.getString("last_name");
            String orderNumber = dataEntry.getString("order_number");
            String customerReachable = dataEntry.getString("customer_reachable_on_phone");
            String deliveryAttempt =  dataEntry.getString("delivery_attempt");
            String delivery_address = dataEntry.getString("delivery_address");
            String delivery_lat = dataEntry.getString("delivery_lat");
            String delivery_long = dataEntry.getString("delivery_long");
            String delivery_successful = dataEntry.getString("delivery_successful");
            String delivery_failure_reason = dataEntry.getString("delivery_failure_reason");
            String delivery_returned_to_pharmacist = dataEntry.getString("delivery_returned_to_pharmacist");

           // String timestampString = "Dec 19, 2023 9:49:42 AM";
           // Date created_at = formatter.parse(dataEntry.get("created_at").toString());

            String dateString = dataEntry.get("created_at").toString();
            Instant instant = Instant.parse(dateString);

            // Convert to LocalDateTime in a specific timezone
            LocalDateTime localDateTime = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();

            // Print out the parsed date
            System.out.println("Parsed date: " + localDateTime);

            // You can also format the LocalDateTime if needed
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = localDateTime.format(formatter);

           String created_at = localDateTime.format(formatter);// dataEntry.get("created_at");
             String updated_at = (String) dataEntry.get("updated_at");

            List<KashaDeliveries> kashaDeliveries = kashaDeliveriesService.getOrderNumber(orderNumber);
            if(kashaDeliveries.size()>0){
                KashaDeliveries kd = kashaDeliveries.get(0);
                kd.setPerson_id(personId);
                kd.setFirst_name(first_name);
                kd.setLast_name(last_name);
                kd.setOrder_number(orderNumber);
                kd.setCustomer_reachable_on_phone(customerReachable);
                kd.setIdentifier(identifier);
                kd.setDelivery_attempt(deliveryAttempt);
                kd.setDelivery_address(delivery_address);
                kd.setDelivery_lat(delivery_lat);
                kd.setDelivery_long(delivery_long);
                kd.setDeliverySuccessful(delivery_successful);
                kd.setDelivery_failure_reason(delivery_failure_reason);
                kd.setDelivery_returned_to_pharmacist(delivery_returned_to_pharmacist);
                kd.setCreated_at(created_at);
                kd.setUpdated_at(updated_at);
                kd.setCreated_on(nowDate);
                kd.setCreated_by(1);
                if(personId==0) {

                }else {
                    kashaDeliveriesService.save(kd);
                }

            }else{
                KashaDeliveries kd = new KashaDeliveries();
                kd.setPerson_id(personId);
                kd.setFirst_name(first_name);
                kd.setLast_name(last_name);
                kd.setOrder_number(orderNumber);
                kd.setCustomer_reachable_on_phone(customerReachable);
                kd.setIdentifier(identifier);
                kd.setDelivery_attempt(deliveryAttempt);
                kd.setDelivery_address(delivery_address);
                kd.setDelivery_lat(delivery_lat);
                kd.setDelivery_long(delivery_long);
                kd.setDeliverySuccessful(delivery_successful);
                kd.setDelivery_failure_reason(delivery_failure_reason);
                kd.setDelivery_returned_to_pharmacist(delivery_returned_to_pharmacist);
                kd.setCreated_at(created_at);
                kd.setUpdated_at(updated_at);
                kd.setCreated_on(nowDate);
                kd.setCreated_by(1);

                if(personId==0) {

                }else {
                    kashaDeliveriesService.save(kd);
                }
            }


        }


    }

     @Scheduled(cron = "0 */30 * ? * *")
    //@Scheduled(cron = "0 0,30 * * * *") // 30 minutes
    public void KashaClients() throws JSONException, ParseException, SQLException, IOException {
        // HttpSession session= new HttpSession()
        // List<KashaClients> kashaClientsList = kashaClientsServices.getAllDataset();
        //int x = kashaClientsList.size();
        //KashaClients
        Date nowDate = new Date();
        Connection con = DriverManager.getConnection(etl_server, etl_username, etl_password);

        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("select \n" +
                "p.person_id,\n" +
                "pii.identifier,\n" +
                "pn.given_name first_name,\n" +
                "pn.family_name last_name,\n" +
                "p.birthdate,\n" +
                "p.gender,\n" +
                "e.encounter_datetime date_consented,\n" +
                "padd.address1 county,\n" +
                "padd.address2 address,\n" +
                "padd.address3 estate_village,\n" +
                "max(case when pa.person_attribute_type_id=10 then pa.value else NULL end) Phone_Number,\n" +
                "max(case when pa.person_attribute_type_id=40 then pa.value else NULL end) Alternative_Phone_Number,\n" +
                "max(hiv.next_clinical_rtc_date_hiv) tca,\n" +
                "padd.address3 nearest_landmark,\n" +
                "max(case when o.concept_id=12395 && o.value_coded =1065 && o.voided=0 then 1 else 0 end) eligibler\n" +
                "from \n" +
                "amrs.encounter e\n" +
                "inner join amrs.obs o on o.encounter_id=e.encounter_id\n" +
                "inner join amrs.person p on p.person_id=e.patient_id\n" +
                "inner join amrs.patient_identifier pii on pii.patient_id=e.patient_id\n" +
                "inner join amrs.person_name pn on pn.person_id = p.person_id and pn.preferred=1\n" +
                "inner join amrs.person_attribute pa on pa.person_id = p.person_id and pa.person_attribute_type_id in(10,40,31,41)\n" +
                "inner join amrs.person_address padd on padd.person_id =p.person_id and padd.preferred=1\n" +
                "inner join etl.flat_hiv_summary_v15b hiv on hiv.person_id=p.person_id\n" +
                "where e.encounter_type=287 and pii.identifier_type=28\n" +
                "group by p.person_id");

        // ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + id + "");
        rs.last();
        //  x = rs.getRow();

        rs.beforeFirst();
        while (rs.next()) {
            String person_id = rs.getString(1).toString();
            String ccc = rs.getString(2).toString();
            String first_name = rs.getString(3).toString();
            String last_name = rs.getString(4).toString();
            String birthdate = rs.getString(5).toString();
            String gender = rs.getString(6).toString();
            String date_consented = rs.getString(7).toString();
            String county = rs.getString(8);
            String address = rs.getString(9);
            String estate_village = rs.getString(10);
            String phone = rs.getString(11).toString();
            String altphone = rs.getString(12);
            String tca = rs.getString(13);
            String land_mark = rs.getString(14);
            String eligible = rs.getString(15);

            System.out.println("CCC Number " + ccc);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            Date tcaDate = formatter.parse(tca);
            Date dateConsented = formatter.parse(date_consented);

            List<KashaClients> kashaClients = kashaClientsServices.getByIdentifier(ccc);
            if (kashaClients.size() > 0) {
                KashaClients kc =   kashaClients.get(0);
                kc.setEligible(Integer.parseInt(eligible));
                kashaClientsServices.save(kc);

            } else {
                KashaClients kc = new KashaClients();
                UUID uuid = UUID.randomUUID();
                kc.setUuid(String.valueOf(uuid));
                kc.setPerson_id(Integer.parseInt(person_id));
                kc.setIdentifier(ccc);
                kc.setAge(birthdate);
                kc.setCounty(county);
                kc.setAddress(address);
                kc.setDateConsented(dateConsented);
                kc.setConsented(1);
                kc.setCreated_by(1);
                kc.setEstate_village(estate_village);
                kc.setExpected_next_delivery_date(tcaDate);
                kc.setFirst_name(first_name);
                kc.setLast_name(last_name);
                kc.setNearest_landmark(land_mark);
                kc.setPhone_number(phone);
                kc.setSecondary_phone_number(altphone);
                kc.setGender(gender);
                kc.setDateCreated(nowDate);
                kc.setModifiedOn(nowDate);
                kc.setEligible(Integer.parseInt(eligible));
                kashaClientsServices.save(kc);
            }


        }
        con.close();


    }


   //  @Scheduled(cron="0 0,30 * * * *") // 30 minutes
    // @Scheduled(cron = "0 */1 * ? * *")
    public void KashaDrugsSync() throws JSONException, ParseException, SQLException, IOException {
        Date nowDate = new Date();
        Connection con = DriverManager.getConnection(etl_server, etl_username, etl_password);
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(" select \n" +
                " e.patient_id,\n" +
                " max(e.encounter_id),\n" +
                " max(e.encounter_datetime) encounterdatetime, \n" +
                " max(case when o.concept_id=5096 then o.value_datetime end) Clinical_TCA,\n" +
                " max(case when o.concept_id=9605 then o.value_datetime end) Medication_Pick_TCA,\n" +
                " max(case when o.concept_id=1088 then cnn.name end) Drug,\n" +
                " datediff((max(date(case when o.concept_id=5096 then o.value_datetime end))),(max(date(case when o.concept_id=9605 then o.value_datetime end)))) Qty,\n" +
                " case when max(case when o.concept_id=9605 then o.value_datetime end) >now() then 1 else 0 end status,\n" +
                "  \"ARV\" medication_type\n" +
                " from amrs.encounter e\n" +
                " inner join amrs.obs o on e.encounter_id=o.encounter_id   and o.concept_id in(1088,9605,5096)\n" +
                " inner join amrs.concept_name cn on cn.concept_id=o.concept_id and cn.voided =0  and cn.locale_preferred=1\n" +
                " left join amrs.concept_name cnn on cnn.concept_id=o.value_coded and cnn.voided =0 and cnn.locale_preferred=1\n" +
                " inner join ampath_spot_live.kasha_clients kc on kc.person_id= e.patient_id\n" +
                " where \n" +
                " e.encounter_type in (2)\n" +
                " group by e.patient_id\n" +
                " order by encounter_datetime desc limit 100");
        // ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + id + "");
        rs.last();
        //  x = rs.getRow();

        rs.beforeFirst();
        while (rs.next()) {
            String patient_id = rs.getString(1).toString();
            String encounter_id = rs.getString(2).toString();
            String encounterdatetime = rs.getString(3).toString();
            String Clinical_TCA = rs.getString(4);
            String Medication_Pick_TCA = rs.getString(5);
            String Drug = rs.getString(6).toString();
            String Qty = rs.getString(7);
            String status = rs.getString(8);
            String medication_type =rs.getString(9);


            System.out.println("CCC Number " + patient_id);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            //  Date tcaDate = formatter.parse(tca);
            //  Date dateConsented =formatter.parse(date_consented);

            List<KashaDrugs> kashaDrugs = kashaDrugService.findByEncounterId(encounter_id);
            if (kashaDrugs.size() > 0) {
               // findByEncounterId
            } else {
                KashaDrugs kd = new KashaDrugs();
                kd.setPersonId(patient_id);
                kd.setEncounterId(encounter_id);
                kd.setClinicalDate(Clinical_TCA);
                kd.setEncounterDate(encounterdatetime);
                kd.setMedicalPickUpTCA(Medication_Pick_TCA);
                kd.setStatus(status);
                kd.setDrug(Drug);
                kd.setQty(Qty);
                kd.setCreated_by(1);
                kd.setDateCreated(nowDate);
                kd.setMedicationType(medication_type);
                kashaDrugService.save(kd);

            }
        }

        con.close();
    }

     // @Scheduled(cron = "0 */1 * ? * *")
    public void updatedpendulum() throws JSONException, ParseException, SQLException, IOException {
        Date nowDate = new Date();
        Connection con = DriverManager.getConnection(etl_server, etl_username, etl_password);
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(" select * from  ampath_spot_live.pendullum_data_new_new");
        // ResultSet rs = stmt.executeQuery("select uuid from afyastat.location where location_id=" + id + "");
        rs.last();
        //  x = rs.getRow();

        rs.beforeFirst();
        while (rs.next()) {
            String p1 = rs.getString(1);
            String p2 = rs.getString(2);
            String p3 = rs.getString(3);
            String p4 = rs.getString(4);
            String p5 = rs.getString(5);
            String p6 = rs.getString(6);
            String p7 = rs.getString(7);
            String p8 = rs.getString(8);
            String p9 = rs.getString(9);
            String p10 = rs.getString(10);
            String p11 = rs.getString(11);
            String p12 = rs.getString(12);
            String p13 = rs.getString(13);
            String p14 = rs.getString(14);
            String p15 = rs.getString(15);
            String p16 = rs.getString(16);
            String p17 = rs.getString(17);
            String p18 = rs.getString(18);
            String p19 = rs.getString(19);
            String p20 = rs.getString(20);
            String p21 = rs.getString(21);

            List<PendullumData> pendullumData = pendulumDataService.FindbyIdandEdate(p1,p5);

            if(pendullumData.size()>0){

            }else {

                PendullumData pd = new PendullumData();
                pd.setPatientIdentifier(p1);
                pd.setGender(p3);
                pd.setBirthdate(p4);
                pd.setEncounterDate(p5);
                pd.setHeight(p6);
                pd.setWeight(p7);
                pd.setNext_clinical_appointment(p8);
                pd.setDiagnosis_Date(p9);
                pd.setTuberculosis_Treatment_Plan(p10);
                pd.setCurrent_WHO_HIV_Stage(p11);
                pd.setCD4_Count(p12);
                pd.setIs_ART(p13);
                pd.setIs_PMTCT(p14);
                pd.setViral_Load(p15);
                pd.setAdherence(p16);
                pd.setEducation_Level(p17);
                pd.setScreening_For_STI(p18);
                pd.setClassification_Of_Malnutrition(p19);
                pd.setIsoniazid_Use(p20);
                pd.setCotrimoxazole_Use(p21);
                pendulumDataService.save(pd);
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
