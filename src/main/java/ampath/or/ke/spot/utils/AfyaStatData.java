package ampath.or.ke.spot.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.services.AfyastatClientLineListService;

import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AfyaStatData {
    public static void pullAllHTSFromDatabase(String server,String username,String password,AfyastatClientLineListService afyastatClientLineListService) throws ParseException, SQLException, IOException {

        AfyastatClientLineList afyastatClientLineList = afyastatClientLineListService.pullLatestSycedRecord();

        int encounter_idd =afyastatClientLineList.getEncounterId();


        Date nowDate = new Date();
        String query = "select \n" +
                "mfl_codes.mfl_code as MFL,\n" +
                "encounter.patient_id,\n" +
                "pii.identifier cht_uuid,\n" +
                "encounter.encounter_id,\n" +
                "et.name encounter_type,\n" +
                "Date(encounter.encounter_datetime) AS TestDate,\n" +
                "year(encounter.encounter_datetime) AS year,\n" +
                "month(encounter.encounter_datetime) AS month,\n" +
                "encounter.date_created as DataEntryDate,\n" +
                "CONCAT(COALESCE(pn.given_name, ''),' ',COALESCE(pn.middle_name, ''),' ',COALESCE(pn.family_name, '')) as person_name,\n" +
                "person.gender as gender,person.birthdate as birthdate\n" +
                ",location.location_id as location_id,\n" +
                "max(if(obs.concept_id=6749,(case obs.value_coded when 5622 then 'OTHER NON-CODED' when 1965 then 'OUTPATIENT' \n" +
                "\twhen 1862 then 'COMMUNITY HEALTH WORKER' when 1776 then 'PMTCT' when 5485 then 'INPATIENT' \n" +
                "\twhen 9494 then 'MATERNITY DEPARTMENT' when 10649 then 'TB' when 1975 then 'PNC' when 2047 then 'VCT' \n" +
                "\twhen 8707 then 'VMMC' when 2049 then 'HOME BASED TESTING' when 1964 then 'STI' when 10138 then 'Nutrition' \n" +
                "\twhen 7974 then 'Emergency' when 2219 then 'PEDIATRIC OUTPATIENT CLINIC' when 2048 then 'MOBILE VCT' else '' end),'No Data')) as SDP ,\n" +
                "max(if(obs.concept_id=1357,(case obs.value_coded when 703 then 'Positive' \n" +
                "when 664 then 'Negative' when 1138 then 'Inconclusive' when 10766 then 'Invalid' else '' end),'Negative')) as FinalTestResult,\n" +
                "encounter.creator,\n" +
                "pr.person_id as provider_person_id,\n" +
                "pru.username as provider_username,\n" +
                "'ARMS' emr,\n" +
                "DATE_FORMAT(FROM_DAYS(DATEDIFF(Date(encounter.encounter_datetime),person.birthdate)), '%Y')+ 0 AS age\n" +
                "from encounter  \n" +
                "inner join obs on encounter.encounter_id = obs.encounter_id and encounter.patient_id=obs.person_id\n" +
                "inner join person_name pn on pn.person_id=encounter.patient_id and pn.voided=0 \n" +
                "inner join location on location.location_id = encounter.location_id  \n" +
                "inner join person on person.person_id=encounter.patient_id and person.voided=0 \n" +
                "inner join mfl_codes on mfl_codes.location_id = encounter.location_id \n" +
                "inner join encounter_provider ep on ep.encounter_id=encounter.encounter_id \n" +
                "inner join provider pr on pr.provider_id=ep.provider_id \n" +
                "inner join patient_identifier pii on person.person_id=pii.patient_id and pii.identifier_type=45\n" +
                "inner join users pru on pru.system_id = pr.identifier \n" +
                "inner join encounter_type et on et.encounter_type_id=encounter.encounter_type\n" +
                "where encounter.encounter_type=2 and encounter.encounter_id>" + encounter_idd + "\n" +
                "group by encounter.encounter_id, encounter.encounter_id,patient_id order by encounter.encounter_id asc ;";

        System.out.println("Server " + server + " Username " + username + "Password " + password);

        Connection conn = DriverManager.getConnection(server, username, password);
        Statement stmtt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println("Text " + query);
        ResultSet resultSett = stmtt.executeQuery(query);
        String mflcode,pname,gender,sdp,results,provider_name,emr,chtuuid,etype="";
        String pid,eid,lid,creator_id,provider_id,age,encounter_date,entry_date,dob,year,month="";
        while (resultSett.next()) {
            mflcode = resultSett.getString("MFL").toString();
            pname = resultSett.getString("person_name").toString();
            gender = resultSett.getString("gender").toString();
            sdp = resultSett.getString("SDP").toString();
            results = resultSett.getString("FinalTestResult").toString();
            provider_name = resultSett.getString("provider_username").toString();
            pid = resultSett.getString("patient_id").toString();
            eid = resultSett.getString("encounter_id").toString();
            lid = resultSett.getString("location_id").toString();
            creator_id = resultSett.getString("creator").toString();
            provider_id = resultSett.getString("provider_person_id").toString();
            encounter_date = resultSett.getString("TestDate").toString();
            entry_date = resultSett.getString("DataEntryDate").toString();
            dob = resultSett.getString("birthdate").toString();
            age= resultSett.getString("age").toString();
            emr= resultSett.getString("emr").toString();
            chtuuid= resultSett.getString("cht_uuid").toString();
            etype= resultSett.getString("encounter_type").toString();
            year=resultSett.getString("year").toString();
            month=resultSett.getString("month").toString();



            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone("Africa/Nairobi"));

            SimpleDateFormat formatterr = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            formatterr.setTimeZone(TimeZone.getTimeZone("Africa/Nairobi"));

            // String dateInString = "22-01-2015 10:15:55 AM";
            Date edate = formatter.parse(entry_date);
            Date dobb = formatterr.parse(dob);
            Date ecdate = formatterr.parse(encounter_date);

          //  System.out.println("MFLCODE "+ mflcode+" "+pname +" " + gender+" "+ sdp +" "+results+" "+provider_name+" "+age+" Encounter  "+encounter_date +" Entry date "+entry_date+" DOB "+dob);

           AfyastatClientLineList ac = new AfyastatClientLineList();
           Double x = Double.parseDouble(age);
           ac.setAge( (int)Math.round(x));
           ac.setEmr(emr);
           ac.setBirthDate(dobb);
           ac.setDataEntryDate(edate);
           ac.setTestDate(ecdate);
           ac.setProvider(provider_name);
           ac.setProviderId(Integer.parseInt(provider_id));
           ac.setCreatorId(Integer.parseInt(creator_id));
           ac.setLocationId(Integer.parseInt(lid));
           ac.setEncounterId(Integer.parseInt(eid));
           ac.setPersonId(Integer.parseInt(pid));
           ac.setFinalTestResult(results);
           ac.setSdp(sdp);
           ac.setGender(gender);
           ac.setPatinetName(pname);
           ac.setMfl(Integer.parseInt(mflcode));
           ac.setChtUuid(chtuuid);
           ac.setCreated_by(1);
           ac.setCreated_on(nowDate);
           ac.setEncounterType(etype);
           ac.setEntryPoint(sdp);
           afyastatClientLineListService.save(ac);




            // facilities.setTxcurr(xxxx);
            String stmt = "";
           /* if (afyastatClientLineList == null) {
                //Pick top 1000 records
                stmt = "";
            } else {
                //Pick sub sequent 100 records every 3 minutes
                Date data_entry_date = afyastatClientLineList.getDataEntryDate();
                stmt = "";
                System.out.println("Last Entry Date " + data_entry_date);

            }
            */
        }
    }
}



