package ampath.or.ke.spot.scheduling;

import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.SurgeReport;
import ampath.or.ke.spot.models.VLResults;
import ampath.or.ke.spot.services.AfyastatErrorsService;
import ampath.or.ke.spot.services.FacilitiesService;
import ampath.or.ke.spot.services.SurgeService;
import ampath.or.ke.spot.services.VLResultsService;
import com.google.api.client.util.DateTime;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CareTasks {
    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;
    @Value("${spring.etl_pot.db}")
    public String db;


    @Autowired
    private SurgeService surgeService;

    @Autowired
    private FacilitiesService facilitiesService;

    @Autowired
    private VLResultsService vlResultsService;


    //@Scheduled(cron = "${com.scheduled.cron}")
    //@Scheduled(cron = "*/130 * * * * *") //seconds
    //@Scheduled(cron="0 0,30 * * * *") // 30 minutes
    public void ProcessSurgeData() throws JSONException, ParseException, SQLException, IOException {
        SurgeReport surgeReport = surgeService.getTopRecord();
        if (surgeReport == null) {
            System.out.println("Surge Rows are null working on the Databases ");
            Connection con = DriverManager.getConnection(server + db, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("CALL surge_synce()");
            rs.last();
            int x = rs.getRow();
            rs.beforeFirst();
            for (int y = 0; y < x; y++) {
                int person_id = Integer.parseInt(rs.getString("person_id").toString());
                String cccno = "test"; //rs.getString("ccc_number").toString();
                int year_week = Integer.parseInt(rs.getString("year_week"));
                int Scheduled_week = Integer.parseInt(rs.getString("scheduled_this_week"));
                String gender = rs.getString("gender");
                // String  upi = rs.getString("upi_number").toString();
                Float age = Float.valueOf(rs.getString("age").toString());
                List<SurgeReport> surgeReportList = surgeService.getPersonPerWeek(person_id, year_week);
                if (surgeReportList.size() == 0) {
                    SurgeReport sg = new SurgeReport();
                    sg.setAge(age);
                    sg.setPersonId(person_id);
                    sg.setYearWeek(year_week);
                    sg.setScheduled_this_week(Scheduled_week);
                    sg.setGender(gender);
                    surgeService.save(sg);
                } else {
                    SurgeReport sg = surgeReportList.get(0);
                    sg.setAge(age);
                    sg.setPersonId(person_id);
                    sg.setYearWeek(year_week);
                    sg.setScheduled_this_week(Scheduled_week);
                    sg.setGender(gender);
                    surgeService.save(sg);
                }
                System.out.println("Surge Rows ara " + x + " " + person_id + " ccc " + cccno + " week " + year_week + " size saved " + surgeReportList.size());
            }

        } else {
            System.out.println("Surge Rows are null working on the Databases ");
            Connection con = DriverManager.getConnection(server + db, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("CALL surge_synce()");
            rs.last();
            int x = rs.getRow();
            rs.beforeFirst();
            while (rs.next()) {
                int person_id = Integer.parseInt(rs.getString("person_id").toString());
                String cccno = "test"; //rs.getString("ccc_number").toString();
                int year_week = Integer.parseInt(rs.getString("year_week"));
                int Scheduled_week = Integer.parseInt(rs.getString("scheduled_this_week"));
                String gender = rs.getString("gender");
                // String  upi = rs.getString("upi_number").toString();
                Float age = Float.valueOf(rs.getString("age").toString());
                Date encounter_date = Date.valueOf(rs.getString("encounter_date"));
                int location_id = Integer.parseInt(rs.getString("location_id"));
                String location = rs.getString("location");

                List<SurgeReport> surgeReportList = surgeService.getPersonPerWeek(person_id, year_week);

                if (surgeReportList.size() == 0) {
                    SurgeReport sg = new SurgeReport();
                    sg.setAge(age);
                    sg.setPersonId(person_id);
                    sg.setYearWeek(year_week);
                    sg.setScheduled_this_week(Scheduled_week);
                    sg.setGender(gender);
                    sg.setEncounterDate(encounter_date);
                    sg.setLocationId(location_id);
                    sg.setLocationName(location);
                    surgeService.save(sg);
                } else {
                    SurgeReport sg = surgeReportList.get(0);
                    sg.setAge(age);
                    sg.setPersonId(person_id);
                    sg.setYearWeek(year_week);
                    sg.setScheduled_this_week(Scheduled_week);
                    sg.setEncounterDate(encounter_date);
                    sg.setGender(gender);
                    sg.setLocationId(location_id);
                    sg.setLocationName(location);
                    surgeService.save(sg);
                }

                System.out.println("Surge Rows ara " + x + " " + person_id + " ccc " + cccno + " week " + year_week + " size saved " + surgeReportList.size());


            }
        }
    }

    //@Scheduled(cron = "*/60 * * * * *")
    public void ProcessMFLCODES() throws JSONException, ParseException, SQLException, IOException {
        List<SurgeReport> surgeReport = surgeService.getNullMFLCODE(0);
        if (surgeReport.size() > 0) {

            for (int x = 0; x < surgeReport.size(); x++) {

                SurgeReport sg = surgeReport.get(x);
                int location_id = sg.getLocationId();
                System.out.println(location_id);
                List<String> amrsIDs = Arrays.asList(String.valueOf(location_id));


                System.out.println(amrsIDs.get(0).toString());
                Facilities facilities = facilitiesService.searchByAMRSID(amrsIDs);
                if (facilitiesService.searchByAMRSID(amrsIDs) == null) {
                    System.out.println(location_id + " Missing in the facilities");

                } else {

                    int mflcode = Integer.parseInt(facilitiesService.searchByAMRSID(amrsIDs).getMflcode());

                    sg.setMflcode(mflcode);
                    surgeService.save(sg);
                    System.out.println("MFLCode" + mflcode);
                }
                //amrsIDs.clear();


            }
        }
    }

    //@Scheduled(cron = "*/60 * * * * *")
    public void ProcessVLResults() throws JSONException, ParseException, SQLException, IOException {
        VLResults vlResults = vlResultsService.getRecenet();
        if (vlResults == null) {
            Connection con = DriverManager.getConnection(server + db, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select  l.person_id,pii.identifier ccc,Date(l.test_datetime) Date_collected, l.hiv_viral_load,l.date_created \n" +
                    " from etl.flat_labs_and_imaging l \n" +
                    " inner join(select pii.identifier,pii.patient_id from  amrs.patient_identifier pii where pii.identifier_type=28) pii on pii.patient_id=l.person_id \n" +
                     " where l.test_datetime >='2023-01-01' and l.test_datetime<=now() order by l.date_created asc");
            rs.last();
            int x = rs.getRow();
            rs.first();
            System.out.println("rows Here "+ x);
            while (rs.next()) {
                int person_id = Integer.parseInt(rs.getString("person_id").toString());
                String ccc = (rs.getString("ccc").toString());
                Date dateCollected  = Date.valueOf((rs.getString("Date_collected")));
                String viralLoad  = (rs.getString("hiv_viral_load"));
                String dateCreated  = (rs.getString("date_created").toString());
                System.out.println("Person Here "+ person_id +" ccc "+ ccc+ " dateCollected "+ dateCollected +" viralLoad "+ viralLoad+ " dateCreated "+dateCreated);

                 VLResults vl = new VLResults();
                 vl.setCcc(ccc);
                 vl.setDateCollected(dateCollected);
                 vl.setViralLoad(viralLoad);
                 vl.setPersonId(person_id);
                 vl.setDateCreated(dateCreated);
                 vlResultsService.save(vl);

            }


        } else {
            System.out.println("Latest VLS are " + vlResults.getDateCreated() );
            Connection con = DriverManager.getConnection(server + db, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select  l.person_id,pii.identifier ccc,Date(l.test_datetime) Date_collected, l.hiv_viral_load,l.date_created \n" +
                    " from etl.flat_labs_and_imaging l \n" +
                    " inner join(select pii.identifier,pii.patient_id from  amrs.patient_identifier pii where pii.identifier_type=28) pii on pii.patient_id=l.person_id \n" +
                    " where l.test_datetime >='2023-08-01' and l.test_datetime <='"+ vlResults.getDateCreated() +"' and l.test_datetime<=now()   order by l.date_created asc");
            rs.last();
            int x = rs.getRow();
            rs.first();
            System.out.println("rows Here "+ x);
            while (rs.next()) {
                int person_id = Integer.parseInt(rs.getString("person_id").toString());
                String ccc = (rs.getString("ccc").toString());
                Date dateCollected  = Date.valueOf((rs.getString("Date_collected")));
                String viralLoad  = (rs.getString("hiv_viral_load"));
                String dateCreated  = (rs.getString("date_created").toString());
                System.out.println("Person Here "+ person_id +" ccc "+ ccc+ " dateCollected "+ dateCollected +" viralLoad "+ viralLoad+ " dateCreated "+dateCreated);
                List<VLResults> vls = vlResultsService.getCCCAndDateCllectd(ccc,dateCollected);
                if(vls.size()==0){
                                VLResults vl = new VLResults();
                                vl.setCcc(ccc);
                                vl.setDateCollected(dateCollected);
                                vl.setViralLoad(viralLoad);
                                vl.setPersonId(person_id);
                                vl.setDateCreated(dateCreated);
                                vlResultsService.save(vl);
                }else{
                    VLResults vr =  vls.get(0);
                    vr.setCcc(ccc);
                    vr.setDateCollected(dateCollected);
                    vr.setViralLoad(viralLoad);
                    vr.setPersonId(person_id);
                    vr.setDateCreated(dateCreated);
                    vlResultsService.save(vr);
                }
      con.close();

            }
        }
    }
}
