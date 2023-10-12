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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class AfyaStatData {

    private static final Logger logger = LoggerFactory.getLogger(AfyaStatData.class);
    @Autowired
    private AfyastatClientLineListService htsService;

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

   /* @Value("${spring.metabase.api.url}")
    private String strURL;
    @Value("${spring.metabase.api.username}")
    private String strUsername;

    @Value("${spring.metabase.api.password}")
    private String strPassword;

    @Value("${spring.metabase.api.queryId}")
    private String strQueryId;*/

    @Value("${spring.afyastat.server}")
    private String server;

    @Value("${spring.afyastat.username}")
    private String username;

    @Value("${spring.afyastat.password}")
    private String password;

    public void pullAllHTSFromDatabase() throws ParseException, SQLException, IOException {
    }

<<<<<<< HEAD


    private static XSSFWorkbook getTemplate() {
        File file;
        XSSFWorkbook workbook = null;
        try {
            file = new ClassPathResource("/setup/AMEP_Facilities_FY21_Version_2.xlsx").getFile();
            //file = new ClassPathResource("D:/eclipse-workspace/skeleton_app/src/main/resources/setup/AMEP_Facilities_FY21_Version_2.xlsx").getFile();
            FileInputStream fileInputStream = new FileInputStream(file);

            workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return workbook;
    }

    public static List<AfyastatClientLineList> getHTSDataFromExcel() {
        List<AfyastatClientLineList> allAfyastatClientLineList = new ArrayList<>();
        try
        {
            XSSFSheet sheet = getTemplate().getSheet("hiv_line_list_per_Site_all"); //User the getTemplate() to access Workbook
            int rowsCount = sheet.getLastRowNum(); //Get the last Row Number which usually starts with Zero
            logger.info("Total Number of Tests: " + (rowsCount + 1));
            //Start reading from row two.
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");//2023-04-13T18:20:50 yyyy-MM-dd'T'HH:mm:ss.SSSZ
            for (int i = 1; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                AfyastatClientLineList afyastatClientLineList = new AfyastatClientLineList();
                afyastatClientLineList.setPersonId((int)row.getCell(0).getNumericCellValue());

                Date date2 = (Date) formatter2.parse(row.getCell(11).getStringCellValue());

                afyastatClientLineList.setTestDate(date2);

                date2 = (Date) formatter3.parse(row.getCell(18).getStringCellValue());

                afyastatClientLineList.setDataEntryDate(date2);
                afyastatClientLineList.setGender(row.getCell(2).getStringCellValue());

                date2 = (Date) formatter2.parse(row.getCell(4).getStringCellValue());

                afyastatClientLineList.setBirthDate(date2);
//                afyastatClientLineList.setLocationId((int)row.getCell(5).getNumericCellValue());
                afyastatClientLineList.setMfl((int)row.getCell(8).getNumericCellValue());
                afyastatClientLineList.setFinalTestResult(row.getCell(19).getStringCellValue());
                //afyastatClientLineList.setEntryPoint(row.getCell(14).getNumericCellValue());
                afyastatClientLineList.setSdp(row.getCell(15).getStringCellValue());
                logger.info("The Client information " + i);
                allAfyastatClientLineList.add(afyastatClientLineList);
            }
            return allAfyastatClientLineList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public List<AfyastatClientLineList> getHTSDataFromDatabase() {
        List<AfyastatClientLineList> allAfyastatClientLineList = new ArrayList<>();
        logger.info("url " + server + "pass " + username + "Password " + password);
//        Connection con = DriverManager.getConnection(this.getServer(), this.getUsername(), this.getStrPassword());
//        int x = 0;
//        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
//
////        try
////        {
//	        ResultSet rs = stmt.executeQuery("select distinct obs.person_id,encounter.encounter_datetime AS TestDate,obs.date_created as DataEntryDate,\n" +
//    		"CONCAT(COALESCE(pn.given_name, ''),' ',COALESCE(pn.middle_name, ''),' ',COALESCE(pn.family_name, '')) as person_name,\n" +
//    		"person.gender as gender,person.birthdate as birthdate,location.location_id as location_id,mfl_codes.mfl_code as MFL,\n" +
//    		"encounter.creator,pru.username as Provider,max(if(obs.concept_id=1357,(case obs.value_coded when 703 then 'Positive' \n" +
//    		"when 664 then 'Negative' when 1138 then 'Inconclusive' when 10766 then 'Invalid' else '' end),null)) as FinalTestResult,\n" +
//    		"entrpoints.hts_entry_point,entrpoints.SDP from obs \n" +
//    		"inner join encounter  on encounter.encounter_id = obs.encounter_id \n" +
//    		"inner join encounter_provider ep on ep.encounter_id=encounter.encounter_id \n" +
//    		"inner join provider pr on pr.provider_id=ep.provider_id \n" +
//    		"inner join users pru on pru.system_id = pr.identifier \n" +
//    		"INNER join form f on f.form_id=encounter.form_id and f.uuid in ('aedd5881-f184-4858-b874-2775cb1119a2') \n" +
//    		"inner join location on location.location_id = obs.location_id  \n" +
//    		"inner join mfl_codes on mfl_codes.location_id = obs.location_id \n" +
//    		"left join person_name pn on pn.person_id=obs.person_id and pn.voided=0 \n" +
//    		"left join person on person.person_id=obs.person_id and person.voided=0 \n" +
//    		"left join(select obs.person_id,obs.encounter_id,max(if(obs.concept_id=6749, obs.value_coded,null)) as hts_entry_point, \n" +
//    		"   max(if(obs.concept_id=6749,(case obs.value_coded when 5622 then 'OTHER NON-CODED' when 1965 then 'OUTPATIENT' \n" +
//    		"	when 1862 then 'COMMUNITY HEALTH WORKER' when 1776 then 'PMTCT' when 5485 then 'INPATIENT' \n" +
//    		"	when 9494 then 'MATERNITY DEPARTMENT' when 10649 then 'TB' when 1975 then 'PNC' when 2047 then 'VCT' \n" +
//    		"	when 8707 then 'VMMC' when 2049 then 'HOME BASED TESTING' when 1964 then 'STI' when 10138 then 'Nutrition' \n" +
//    		"	when 7974 then 'Emergency' when 2219 then 'PEDIATRIC OUTPATIENT CLINIC' when 2048 then 'MOBILE VCT' else '' end),null)) as SDP \n" +
//    		"from obs group by obs.person_id,obs.encounter_id) as entrpoints on obs.person_id = entrpoints.person_id and obs.encounter_id = entrpoints.encounter_id  \n" +
//    		"where obs.concept_id in (1357) and obs.voided=0 group by encounter.encounter_id, encounter.encounter_datetime order by encounter.encounter_datetime limit 1000;");
//
//	        rs.last();
//	        x = rs.getRow();
//	        rs.beforeFirst();
////          while (rs.next()) {
////                AfyastatClientLineList afyastatClientLineList = new AfyastatClientLineList();
////                afyastatClientLineList.setPersonId((int)row.getCell(0).getNumericCellValue());
////                afyastatClientLineList.setTestDate(date2);
////                afyastatClientLineList.setDataEntryDate(date2);
////                afyastatClientLineList.setGender(row.getCell(2).getStringCellValue());
////                afyastatClientLineList.setBirthDate(date2);
////                afyastatClientLineList.setLocationId((int)row.getCell(5).getNumericCellValue());
////                afyastatClientLineList.setMfl((int)row.getCell(8).getNumericCellValue());
////                afyastatClientLineList.setFinalTestResult(row.getCell(19).getStringCellValue());
////                afyastatClientLineList.setEntryPoint(String.valueOf(row.getCell(14).getNumericCellValue()));
////                afyastatClientLineList.setSdp(row.getCell(15).getStringCellValue());
//
////                logger.info("The Client information :" + x);
////                allAfyastatClientLineList.add(afyastatClientLineList);
////           }
////	        return allAfyastatClientLineList;
////        }finally{
//        	stmt.close();
        return null;
//        }
    }

    public String getStrURL() {
        return strURL;
    }

    public void setStrURL(String strURL) {
        this.strURL = strURL;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrQueryId() {
        return strQueryId;
    }

    public void setStrQueryId(String strQueryId) {
        this.strQueryId = strQueryId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
=======
>>>>>>> cf447d1c4f3332402c49bd8ea060747d5b9953ff
}
