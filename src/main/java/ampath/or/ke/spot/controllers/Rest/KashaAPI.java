package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.models.KashaClients;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import ampath.or.ke.spot.services.KashaClientsServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/api/kasha")
public class KashaAPI {
    @Autowired
    private KashaClientsServices kashaClientsServices;
    @ResponseBody
    @RequestMapping(value="/clients", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String HTSlist() throws Exception {
        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        List<KashaClients> kashaClientsList =  kashaClientsServices.getAllDataset();
        String jssons="";
        for (int x=0;x<kashaClientsList.size();x++) {
            KashaClients att = kashaClientsList.get(x);
            Gson gson = new Gson();
            String jsons = gson.toJson(att);//att);
            jssons =  jssons+","+jsons;

        }
        String jstring =jssons.substring(1);
        String fstring = "["+jstring+"]";
        System.out.println("Client Line size "+ fstring);
        return fstring;
    }
}
