package ampath.or.ke.spot.controllers.Rest;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.val;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ampath.or.ke.spot.services.FacilitiesService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@RestController
@CrossOrigin
@RequestMapping("api/v1/facilities")
public class FacilitiesAPI {

 @Autowired
    public FacilitiesService facilitiesService;

  // @GetMapping("/getall",produces = "application/json")
   @RequestMapping(value="/getall", method = RequestMethod.GET,produces = "application/json")//, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public String EMRlist() throws Exception {
        String output = "";
        Date nowDate = new Date();
        List<?>  emrs  = facilitiesService.EMRDistribution();
       JSONArray jsonArray = new JSONArray();
       String jssons="";
       for (int x=0;x<emrs.size();x++) {
           JsonObject json = new JsonObject();
           String emr = Array.get(emrs.get(x), 0).toString();
           int cunt = Integer.parseInt(Array.get(emrs.get(x), 1).toString());

           json.addProperty("value",cunt);
           json.addProperty("name",emr);

           jsonArray.put(json);

       }

        return jsonArray.toString();
    }
    /*
    ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Define map which will be converted to JSON
        List<Person> personList = Stream.of(
                new Person("Mike", "harvey", 34),
                new Person("Nick", "young", 75),
                new Person("Jack", "slater", 21 ),
                new Person("gary", "hudson", 55))
                .collect(Collectors.toList());

        //1. Convert List of Person objects to JSON
        String arrayToJson = objectMapper.writeValueAsString(personList);
        System.out.println("1. Convert List of person objects to JSON :");
        System.out.println(arrayToJson);

        //2. Convert JSON to List of Person objects
        //Define Custom Type reference for List<Person> type
        TypeReference<List<Person>> mapType = new TypeReference<List<Person>>() {};
        List<Person> jsonToPersonList = objectMapper.readValue(arrayToJson, mapType);
        System.out.println("\n2. Convert JSON to List of person objects :");

        //Print list of person objects output using Java 8
        jsonToPersonList.forEach(System.out::println);
    }
     */
}

