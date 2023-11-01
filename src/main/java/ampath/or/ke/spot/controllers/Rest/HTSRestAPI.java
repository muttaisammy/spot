package ampath.or.ke.spot.controllers.Rest;

import ampath.or.ke.spot.models.AfyastatClientLineList;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/rest/v1/api/hts")
public class HTSRestAPI {

    @Autowired
    private AfyastatClientLineListService afyastatClientLineListService;

    @ResponseBody
    @RequestMapping(value="/getall", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String HTSlist() throws Exception {
        String output = "";
        Date nowDate = new Date();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        List<AfyastatClientLineList> afyastatClientLineListList =  afyastatClientLineListService.getFirst1000Tests();
       String jssons="";
        for (int x=0;x<afyastatClientLineListList.size();x++) {
            AfyastatClientLineList att = afyastatClientLineListList.get(x);
            Gson gson = new Gson();
            String jsons = gson.toJson(att);//att);
            jssons =  jssons+","+jsons;

        }
        String jstring =jssons.substring(1);
        String fstring = "["+jstring+"]";
        System.out.println("Client Line size "+ fstring);
        return fstring;
    }

    @ResponseBody
    @RequestMapping(value="/maps", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String Mapslist() throws Exception {
    String maps="[\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 22, \"Name\": \"Liu, Laurin\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Bloc Quebecois\", \"Age\": 43, \"Name\": \"Mourani, Maria\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": \"\", \"Name\": \"Sellah, Djaouida\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 72, \"Name\": \"St-Denis, Lise\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Liberal\", \"Age\": 71, \"Name\": \"Fry, Hedy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 70, \"Name\": \"Turmel, Nycole\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 68, \"Name\": \"Sgro, Judy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 67, \"Name\": \"Raynault, Francine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 66, \"Name\": \"Davidson, Patricia\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Smith, Joy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 64, \"Name\": \"Wong, Alice\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"O'Neill Gordon, Tilly\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"Ablonczy, Diane\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"NDP\", \"Age\": 63, \"Name\": \"Duncan, Linda Francis\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 62, \"Name\": \"Bennett, Carolyn\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 61, \"Name\": \"Nash, Peggy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 61, \"Name\": \"Mathyssen, Irene\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 60, \"Name\": \"Sims, Jinny Jogindera\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"Liberal\", \"Age\": 60, \"Name\": \"Foote, Judy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 60, \"Name\": \"Crowder, Jean\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Davies, Libby\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 59, \"Name\": \"Yelich, Lynne\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 58, \"Name\": \"Day, Anne-Marie\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Green\", \"Age\": 58, \"Name\": \"May, Elizabeth\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Liberal\", \"Age\": 58, \"Name\": \"Murray, Joyce\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Findlay, Kerry-Lynne D.\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Brown, Lois\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 57, \"Name\": \"Laverdière, Hélène\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 57, \"Name\": \"Boutin-Sweet, Marjolaine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 56, \"Name\": \"Crockatt, Joan\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 55, \"Name\": \"Chow, Olivia\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"McLeod, Cathy\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"Finley, Diane\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 54, \"Name\": \"LeBlanc, Hélène\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Grewal, Nina\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 54, \"Name\": \"Hughes, Carol\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Prince Edward Island\", \"Party\": \"Conservative\", \"Age\": 53, \"Name\": \"Shea, Gail\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 53, \"Name\": \"Truppe, Susan\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 52, \"Name\": \"Young, Wai\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 52, \"Name\": \"Gallant, Cheryl\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 52, \"Name\": \"Boivin, Françoise\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 51, \"Name\": \"Block, Kelly\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Ayala, Paulina\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Groguhé, Sadia\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 49, \"Name\": \"Charlton, Chris\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Bergen, Candice\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 46, \"Name\": \"Perreault, Manon\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 46, \"Name\": \"James, Roxanne\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 46, \"Name\": \"Ambler, Stella\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 46, \"Name\": \"Duncan, Kirsty\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Glover, Shelly\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Territories\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Aglukkaq, Leona\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 44, \"Name\": \"Raitt, Lisa\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 43, \"Name\": \"Ambrose, Rona\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Leitch, Kellie\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"NDP\", \"Age\": 39, \"Name\": \"Leslie, Megan\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 38, \"Name\": \"Hassainia, Sana\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Adams, Eve\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 32, \"Name\": \"Rempel, Michelle\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 32, \"Name\": \"Papillon, Annick\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 31, \"Name\": \"Sitsabaiesan, Rathika\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 30, \"Name\": \"Quach, Anne Minh-Thu\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"NDP\", \"Age\": 30, \"Name\": \"Ashton, Niki\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 29, \"Name\": \"Moore, Christine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Morin, Isabelle\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Blanchette-Lamothe, Lysane\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Brosseau, Ruth Ellen\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Latendresse, Alexandrine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Doré Lefebvre, Rosane\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 27, \"Name\": \"Morin, Marie-Claude\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 27, \"Name\": \"Michaud, Élaine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 24, \"Name\": \"Péclet, Ève\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 23, \"Name\": \"Freeman, Mylène\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 22, \"Name\": \"Borg, Charmaine\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": \"\", \"Name\": \"Bateman, Joyce\", \"Gender\": \"Female\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 43, \"Name\": \"Hiebert, Russ\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Jacob, Pierre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Vellacott, Maurice\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 75, \"Name\": \"Boughen, Ray\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 73, \"Name\": \"O'Connor, Gordon\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 72, \"Name\": \"Cotler, Irwin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 72, \"Name\": \"Oliver, Joe\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 71, \"Name\": \"Tilson, David Allan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 70, \"Name\": \"Fantino, Julian\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 69, \"Name\": \"Kent, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Bloc Quebecois\", \"Age\": 69, \"Name\": \"Plamondon, Louis\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 69, \"Name\": \"Schellenberger, Gary\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 68, \"Name\": \"Lauzon, Guy\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 68, \"Name\": \"Harris, Richard M.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 68, \"Name\": \"Goldring, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 67, \"Name\": \"Atamanenko, Alex\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 67, \"Name\": \"Payne, LaVar\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 67, \"Name\": \"Breitkreuz, Garry W.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 66, \"Name\": \"Genest, Réjean\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 66, \"Name\": \"MacKenzie, Dave\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 66, \"Name\": \"Hyer, Bruce\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Prince Edward Island\", \"Party\": \"Liberal\", \"Age\": 66, \"Name\": \"MacAulay, Lawrence\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Galipeau, Royal\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 65, \"Name\": \"Marston, Wayne\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Hawn, Laurie\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Kramp, Daryl\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Shipley, Bev\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Conservative\", \"Age\": 65, \"Name\": \"Kerr, Greg\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 65, \"Name\": \"Comartin, Joe\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 64, \"Name\": \"Norlock, Rick\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 64, \"Name\": \"McKay, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 64, \"Name\": \"Mayes, Colin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 64, \"Name\": \"Rae, Bob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"NDP\", \"Age\": 64, \"Name\": \"Harris, Jack\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 64, \"Name\": \"Duncan, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"Chisu, Corneliu\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 63, \"Name\": \"Garneau, Marc\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Prince Edward Island\", \"Party\": \"Liberal\", \"Age\": 63, \"Name\": \"Easter, Arnold Wayne\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"Aspin, Jay\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Liberal\", \"Age\": 63, \"Name\": \"Goodale, Ralph\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"Albrecht, Harold\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 63, \"Name\": \"Gravelle, Claude\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 63, \"Name\": \"Komarnicki, Ed\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 62, \"Name\": \"Flaherty, James Michael (Jim)\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 62, \"Name\": \"Rankin, Murray\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 62, \"Name\": \"McCallum, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 62, \"Name\": \"Warawa, Mark\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 62, \"Name\": \"Obhrai, Deepak\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 62, \"Name\": \"Benoit, Leon Earl\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 62, \"Name\": \"Leung, Chungsen\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 61, \"Name\": \"Morin, Marc-André\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 61, \"Name\": \"Sopuck, Robert\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 61, \"Name\": \"Ritz, Gerry\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 61, \"Name\": \"Garrison, Randall\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 61, \"Name\": \"Lunney, James\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 61, \"Name\": \"Lukiwski, Tom\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Carmichael, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Menzies, Ted\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Valcourt, Bernard\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Ashfield, Keith\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Nicholson, Rob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Young, Terence H.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 60, \"Name\": \"Toews, Vic\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 60, \"Name\": \"Sullivan, Mike\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Patry, Claude\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Conservative\", \"Age\": 59, \"Name\": \"Keddy, Gerald\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Territories\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Bevington, Dennis Fraser\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Allen, Malcolm\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 59, \"Name\": \"Rafferty, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 59, \"Name\": \"Dreeshen, Earl\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 59, \"Name\": \"Kamp, Randy\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 59, \"Name\": \"Merrifield, Rob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"Woodworth, Stephen\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"McColeman, Phil\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"Lebel, Denis\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"Lizon, Wladyslaw\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"Holder, Ed\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 58, \"Name\": \"Valeriote, Frank\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 58, \"Name\": \"Christopherson, David\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 58, \"Name\": \"Mulcair, Thomas J.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 58, \"Name\": \"Daniel, Joe\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 57, \"Name\": \"Karygiannis, Jim\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"NDP\", \"Age\": 57, \"Name\": \"Godin, Yvon\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 57, \"Name\": \"Dionne Labelle, Pierre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Preston, Joe\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 57, \"Name\": \"Bélanger, Mauril\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Fast, Edward\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Tweed, Mervin C.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 57, \"Name\": \"Dion, Stéphane\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 57, \"Name\": \"Van Kesteren, Dave\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Liberal\", \"Age\": 57, \"Name\": \"Cuzner, Rodger\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"NDP\", \"Age\": 57, \"Name\": \"Martin, Pat\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"NDP\", \"Age\": 56, \"Name\": \"Stoffer, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 56, \"Name\": \"Miller, Larry\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 56, \"Name\": \"Blanchette, Denis\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 56, \"Name\": \"Nunez-Melo, José\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"Goguen, Robert\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 55, \"Name\": \"Scarpaleggia, Francis\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"Sweet, David\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"Anderson, David\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"NDP\", \"Age\": 55, \"Name\": \"Chisholm, Robert\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 55, \"Name\": \"Stanton, Bruce\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Goodyear, Gary\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Weston, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Dechert, Bob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Shory, Devinder\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 54, \"Name\": \"Pilon, François\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Hayes, Bryan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 54, \"Name\": \"Giguère, Alain\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 54, \"Name\": \"Sorenson, Kevin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 53, \"Name\": \"Benskin, Tyrone\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 53, \"Name\": \"Menegakis, Costas\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 53, \"Name\": \"Harper, Stephen\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 53, \"Name\": \"Wilks, David\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Liberal\", \"Age\": 53, \"Name\": \"Regan, Geoff\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 52, \"Name\": \"McGuinty, David\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 52, \"Name\": \"Gosal, Bal\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 52, \"Name\": \"Aubin, Robert\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Liberal\", \"Age\": 52, \"Name\": \"Eyking, Mark\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 52, \"Name\": \"Brown, Gordon\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 52, \"Name\": \"Allen, Mike\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 51, \"Name\": \"Clement, Tony\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 51, \"Name\": \"Cannan, Ronald\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 51, \"Name\": \"Rousseau, Jean\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 51, \"Name\": \"Opitz, Ted\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 50, \"Name\": \"Toet, Lawrence\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Cash, Andrew\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Liberal\", \"Age\": 50, \"Name\": \"Lamoureux, Kevin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Scott, Craig\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 50, \"Name\": \"Adler, Mark\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 50, \"Name\": \"Carrie, Colin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Julian, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 50, \"Name\": \"Pacetti, Massimo\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Saganash, Romeo\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 50, \"Name\": \"Angus, Charlie\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 49, \"Name\": \"Davies, Don\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Bernier, Maxime\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 49, \"Name\": \"Dewar, Paul\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Jean, Brian\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Devolin, Barry\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Lemieux, Pierre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Van Loan, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Prince Edward Island\", \"Party\": \"Liberal\", \"Age\": 49, \"Name\": \"Casey, Sean\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 49, \"Name\": \"Nantel, Pierre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 49, \"Name\": \"Coderre, Denis\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 49, \"Name\": \"Wallace, Mike\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Braid, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Gourde, Jacques\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Reid, Scott\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Liberal\", \"Age\": 48, \"Name\": \"Hsu, Ted\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Saxton, Andrew\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Weston, Rodney\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Penashue, Peter\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Bloc Quebecois\", \"Age\": 48, \"Name\": \"Bellavance, André\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 48, \"Name\": \"Rathgeber, Brent\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 48, \"Name\": \"Kellway, Matthew\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 47, \"Name\": \"Toone, Philip\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 47, \"Name\": \"Allison, Dean\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 47, \"Name\": \"Trottier, Bernard\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Conservative\", \"Age\": 47, \"Name\": \"Blaney, Steven\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 47, \"Name\": \"Bezan, James\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Conservative\", \"Age\": 47, \"Name\": \"MacKay, Peter Gordon\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 46, \"Name\": \"Dykstra, Richard\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 46, \"Name\": \"Sandhu, Jasbir\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 46, \"Name\": \"Donnelly, Fin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Conservative\", \"Age\": 46, \"Name\": \"Armstrong, Scott\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"Liberal\", \"Age\": 46, \"Name\": \"Byrne, Gerry\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 46, \"Name\": \"Stewart, Kennedy\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"NDP\", \"Age\": 46, \"Name\": \"Cleary, Ryan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 45, \"Name\": \"Côté, Raymond\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Clarke, Rob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Nova Scotia\", \"Party\": \"Liberal\", \"Age\": 45, \"Name\": \"Brison, Scott\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Butt, Brad\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Rickford, Greg\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Liberal\", \"Age\": 45, \"Name\": \"LeBlanc, Dominic\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 45, \"Name\": \"Hoback, Randy\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 44, \"Name\": \"Caron, Guy\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 44, \"Name\": \"Brahmi, Tarik\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 44, \"Name\": \"Kenney, Jason\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 44, \"Name\": \"Masse, Brian\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 44, \"Name\": \"Alexander, Chris\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 44, \"Name\": \"Zimmer, Bob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 44, \"Name\": \"Calkins, Blaine\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 43, \"Name\": \"Baird, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 43, \"Name\": \"Lake, Mike\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"Liberal\", \"Age\": 43, \"Name\": \"Simms, Scott\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 43, \"Name\": \"Thibeault, Glenn\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Williamson, John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Calandra, Paul\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 42, \"Name\": \"Chicoine, Sylvain\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Del Mastro, Dean\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Rajotte, James\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 42, \"Name\": \"Seeback, Kyle\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 41, \"Name\": \"Watson, Jeff\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 41, \"Name\": \"Lapointe, François\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 41, \"Name\": \"Nicholls, Jamie\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 41, \"Name\": \"Chong, Michael D.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Liberal\", \"Age\": 41, \"Name\": \"Trudeau, Justin\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 40, \"Name\": \"Larose, Jean-François\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 40, \"Name\": \"Anders, Rob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 40, \"Name\": \"Fletcher, Steven John\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"NDP\", \"Age\": 40, \"Name\": \"Cullen, Nathan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 39, \"Name\": \"Ravignat, Mathieu\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Manitoba\", \"Party\": \"Conservative\", \"Age\": 39, \"Name\": \"Bruinooge, Rod\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 39, \"Name\": \"Mai, Hoang\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 39, \"Name\": \"Boulerice, Alexandre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Bloc Quebecois\", \"Age\": 39, \"Name\": \"Fortin, Jean-François\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Territories\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Leef, Ryan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Paradis, Christian\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 38, \"Name\": \"Choquette, François\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"New Brunswick\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Moore, Rob\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Trost, Brad\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Gill, Parm\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Hillyer, Jim\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Richards, Blake\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 38, \"Name\": \"Uppal, Tim\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Newfoundland and Labrador\", \"Party\": \"Liberal\", \"Age\": 37, \"Name\": \"Andrews, Scott\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 36, \"Name\": \"Moore, James\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 36, \"Name\": \"Lobb, Ben\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 36, \"Name\": \"Albas, Dan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 34, \"Name\": \"Storseth, Brian\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"British Columbia\", \"Party\": \"Conservative\", \"Age\": 34, \"Name\": \"Strahl, Mark\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 34, \"Name\": \"Brown, Patrick W.\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Alberta\", \"Party\": \"Conservative\", \"Age\": 34, \"Name\": \"Warkentin, Chris\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Saskatchewan\", \"Party\": \"Conservative\", \"Age\": 33, \"Name\": \"Scheer, Andrew\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": 33, \"Name\": \"Poilievre, Pierre\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 33, \"Name\": \"Genest-Jourdain, Jonathan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"NDP\", \"Age\": 33, \"Name\": \"Harris, Dan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 28, \"Name\": \"Tremblay, Jonathan\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 27, \"Name\": \"Morin, Dany\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 24, \"Name\": \"Dubé, Matthew\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Quebec\", \"Party\": \"NDP\", \"Age\": 21, \"Name\": \"Dusseault, Pierre-Luc\", \"Gender\": \"Male\"},\n" +
            "{\"Province\": \"Ontario\", \"Party\": \"Conservative\", \"Age\": \"\", \"Name\": \"O'Toole, Erin\", \"Gender\": \"Male\"} ]\n";

    return maps;
    }
}
