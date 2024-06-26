package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.CCCno;
import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.CCCService;
import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/cleaner")
public class CleanerController {
    @Value("${spring.openmrs.username}")
    private String openmrs_username;
    @Value("${spring.openmrs.password}")
    private String openmrs_password;
    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;
    @Value("${spring.openmrs.live.authcode}")
    public String authcode;
    @Value("${spring.openmrs.live.url}")
    public String openmrsURL;

    @Autowired
    CCCService cccService;

    @RequestMapping(value = "/list_ccc", method = RequestMethod.GET)
    public String encounters(HttpSession session) throws IOException, JSONException, SQLException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Date nowDate = new Date();
            int x = 0;
            String CCC_SQL = "select p.person_id,p.uuid p_uuid ,pii.identifier, replace(pii.identifier, '-', '') ccno,l.uuid location,'f5782fe3-481c-402e-a721-254bf94e834d' ptype_uuid from patient_identifier pii\n" +
                    "inner join person p on pii.patient_id=p.person_id\n" +
                    "inner join location l on pii.location_id=l.location_id\n" +
                    "inner join patient_identifier_type pit on pii.identifier_type=pit.patient_identifier_type_id\n" +
                    "  where identifier_type =28 and pii.voided=0";

            Connection con = DriverManager.getConnection(server + "/amrs", username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(CCC_SQL);
            rs.last();
            x = rs.getRow();
            rs.beforeFirst();
            while (rs.next()) {
                String pid = rs.getString("person_id");
                String p_uuid = rs.getString("p_uuid");
                String identifier = rs.getString("identifier");
                String ccno = rs.getString("ccno");
                String location_uuid = rs.getString("location");
                String ptype =rs.getString("ptype_uuid");

                CCCno ccCno = cccService.getByPersonId(Integer.parseInt(pid));
                if (ccCno == null) {
                    CCCno c = new CCCno();
                    c.setPersonID(Integer.parseInt(pid));
                    c.setLocationUUID(location_uuid);
                    c.setNewCCC(ccno);
                    c.setOldCCC(identifier);
                    c.setPersonUUID(p_uuid);
                    c.setIdentifierType(ptype);
                    cccService.save(c);
                }
                else{

                    ccCno.setPersonID(Integer.parseInt(pid));
                    ccCno.setLocationUUID(location_uuid);
                    ccCno.setNewCCC(ccno);
                    ccCno.setOldCCC(identifier);
                    ccCno.setPersonUUID(p_uuid);
                    ccCno.setIdentifierType(ptype);
                    cccService.save(ccCno);
                }


            }

            return "Done";
        } else {
            return ("Authirazation is required");
        }
    }

    @RequestMapping(value = "/updateCCC", method = RequestMethod.GET)
    public String cccnos(HttpSession session) throws IOException, JSONException, SQLException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Date nowDate = new Date();

            OkHttpClient sessionclient = new OkHttpClient();
            Request sessionrequest = new Request.Builder()
                    .url( openmrsURL+"session")
                    .method("GET", null)
                    .addHeader("Authorization", "Basic " + authcode)
                    .build();
            Response sessionresponse = sessionclient.newCall(sessionrequest).execute();

            String json = sessionresponse.body().string();
            //System.out.println("Session "+json);
            JSONObject jsonObject = new JSONObject(json);
            String sessionID = (String) jsonObject.get("sessionId");
            String cookie="JSESSIONID="+sessionID;
            session.setAttribute("cookie", cookie);
            List<CCCno> ccCnoList = cccService.getByStatus(0);
            for(int x=0;x<ccCnoList.size();x++) {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("identifier", ccCnoList.get(x).getNewCCC());
                jsonBody.put("identifierType", ccCnoList.get(x).getIdentifierType());
                jsonBody.put("location",ccCnoList.get(x).getLocationUUID());
                jsonBody.put("preferred",false);
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonBody.toString());
                String url = openmrsURL+"/patient/"+ ccCnoList.get(x).getPersonUUID()+"/identifier/";
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Authorization", "Basic " + authcode)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Cookie", (String) session.getAttribute("cookie"))
                        .build();
                Response response = client.newCall(request).execute();
              //  String bb = response.body().string();
                int code =response.code();
                System.out.println(code);
                if(code==201){
                  CCCno c =  ccCnoList.get(x);
                  c.setStatus(1);
                  cccService.save(c);
                }


            }
            return "Done";
        } else {
            return "Authirazation is required";
        }
    }
}
