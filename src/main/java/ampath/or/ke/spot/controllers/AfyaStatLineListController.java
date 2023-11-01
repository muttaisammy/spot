package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.services.*;
import ampath.or.ke.spot.utils.AfyaStatData;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Controller
@Transactional
@RequestMapping("/afyastatList")
public class AfyaStatLineListController {
    @Value("${spring.afyastat.username}")
    public  String username;
    @Value("${spring.afyastat.password}")
    public String password;
    @Value("${spring.afyastat.server}")
    public  String server;
    @Value("${spring.openmrs.url}")
    private String url;

    @Value("${spring.openmrs.username}")
    private String openmrs_username;
    @Value("${spring.openmrs.password}")
    private String openmrs_password;
    @Autowired
    private AfyastatLineListService afyastatClientLineListService;

    @Autowired
    public ProgramsService programsService;
    @Autowired
    public CountiesService countiesService;
    @Autowired
    public WardService wardService;
    @Autowired
    public FacilitiesService facilitiesService;
    @Autowired
    public SubCountiesService subCountiesService;
    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public ModelAndView processregistartion(HttpSession session) throws IOException, JSONException, SQLException, ParseException {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("pm/afyastatnew");
            Date nowDate = new Date();
            String query = "select \n" +
                    "mfl_codes.mfl_code as MFL,\n" +
                    "encounter.patient_id,\n" +
                    "pii.identifier cht_uuid,\n" +
                    "encounter.encounter_id,\n" +
                    "et.name encounter_type,\n" +
                    "Date(encounter.encounter_datetime) AS TestDate,\n" +
                    "encounter.date_created as DataEntryDate,\n" +
                    "CONCAT(COALESCE(pn.given_name, ''),' ',COALESCE(pn.middle_name, ''),' ',COALESCE(pn.family_name, '')) as person_name,\n" +
                    "person.gender as gender,person.birthdate as birthdate\n" +
                    ",location.location_id as location_id,\n" +
                    "max(if(obs.concept_id=6749,(case obs.value_coded when 5622 then 'OTHER NON-CODED' when 1965 then 'OUTPATIENT' \n" +
                    "\twhen 1862 then 'COMMUNITY HEALTH WORKER' when 1776 then 'PMTCT' when 5485 then 'INPATIENT' \n" +
                    "\twhen 9494 then 'MATERNITY DEPARTMENT' when 10649 then 'TB' when 1975 then 'PNC' when 2047 then 'VCT' \n" +
                    "\twhen 8707 then 'VMMC' when 2049 then 'HOME BASED TESTING' when 1964 then 'STI' when 10138 then 'Nutrition' \n" +
                    "\twhen 7974 then 'Emergency' when 2219 then 'PEDIATRIC OUTPATIENT CLINIC' when 2048 then 'MOBILE VCT' else '' end),null)) as SDP ,\n" +
                    "max(if(obs.concept_id=1357,(case obs.value_coded when 703 then 'Positive' \n" +
                    "when 664 then 'Negative' when 1138 then 'Inconclusive' when 10766 then 'Invalid' else '' end),null)) as FinalTestResult,\n" +
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
                    "where encounter.encounter_type=2\n" +
                    "group by encounter.encounter_id, encounter.encounter_id,patient_id limit 10;";

            System.out.println("Server " + server + " Username " + username + "Password " + password);

            Connection conn = DriverManager.getConnection(server, username, password);
            Statement stmtt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Text " + query);
            ResultSet resultSett = stmtt.executeQuery(query);
            String mflcode,pname,gender,sdp,results,provider_name,emr,chtuuid,etype="";
            String pid,eid,lid,creator_id,provider_id,age,encounter_date,entry_date,dob="";
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
                age = resultSett.getString("age").toString();
                emr = resultSett.getString("emr").toString();
                chtuuid = resultSett.getString("cht_uuid").toString();
                etype = resultSett.getString("encounter_type").toString();

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
                ac.setAge(Integer.parseInt(String.valueOf(age.length() - 1)));
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
                afyastatClientLineListService.save(ac);
            }
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView htsDashboard( HttpSession session) throws IOException, JSONException, SQLException, ParseException {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("counties", countiesService.getAllDataset());
            modelAndView.addObject("partner", programsService.getAllPrograms());
            modelAndView.addObject("subcounty", subCountiesService.getAllDataset());
            modelAndView.addObject("ward", wardService.getAllDataset());
            modelAndView.addObject("facility", facilitiesService.getAllDataset());
           // modelAndView.setViewName("hts_dashboards");
           //  modelAndView.setViewName("afyastatdash");
            modelAndView.setViewName("hts_dash_new");
          //  modelAndView.setViewName("maps");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }
    }
}
