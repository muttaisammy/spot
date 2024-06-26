package ampath.or.ke.spot.controllers;

import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@Transactional
@RequestMapping("/amrs")
public class AMRSIDController {
    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = "application/json")
    public String home() throws IOException {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpClient sessionclient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("user", "1")
                .build();
        Request sessionrequest = new Request.Builder()
               // .url("http://10.50.80.44:8016/generateidentifier")
                .url("https://ngx.ampath.or.ke/amrs-id-generator/generateidentifier")
                .method("POST", formBody)
                .build();
        Response sessionresponse = sessionclient.newCall(sessionrequest).execute();

        String json;

        {
            try {
                json = sessionresponse.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        JSONObject jsonObject;

        {
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        String AMRSID;

        {
            try {
                AMRSID = (String) jsonObject.get("identifier");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
System.out.println("AMRS ID "+ AMRSID);
        return AMRSID;
    }

    @RequestMapping(value = "/newonART", method = RequestMethod.GET, produces = "application/json")
    public String OnART() throws IOException {
        return String.valueOf(1);

    }

}
