package ampath.or.ke.spot.controllers.Rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/rest/v1/api/rri")
public class RRIRestAPI {
    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;
    @Value("${app.dir}")
    public String data_path;
    
    @ResponseBody
    public String RRIlist() throws Exception {
        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        String data = readFileAsString(data_path+"RRI_raw_data.csv");
        //  System.out.println(data);
        JSONArray jsonArray = new JSONArray(data);

        JSONArray filteredObjects =  new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println("Element " + i + ": " + jsonArray.getJSONObject(i));
            JSONObject object = jsonArray.getJSONObject(i);
            String dd  = object.getString("hiv_start_date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(dd);

           // if(date.after(startdate) && date.before(enddate)){
                filteredObjects.put(object);
           // }
            //if(object.hiv_start_date)
            System.out.println(object + " " + date);
        }
        String ndata = filteredObjects.toString();
        //http://localhost/hts/hts_tst?startdate=2023-07-1&enddate=2023-07-3

        return ndata;
    }
    public static String readFileAsString(String fileName)
            throws Exception
    {
        String data = "";
        data = new String(
                Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
