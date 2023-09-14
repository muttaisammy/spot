package ampath.or.ke.spot.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import ampath.or.ke.spot.models.RRI;
import ampath.or.ke.spot.services.RRIService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.apache.poi.ss.usermodel.*;

import ampath.or.ke.spot.models.County;
import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.FacilitiesService;
import ampath.or.ke.spot.services.UserService;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private UserService userService;
    @Autowired
    private FacilitiesService facilitiesService;

    @Autowired
    private RRIService rriService;

    @Value("${app.dir}")
    public String data_path;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public ModelAndView postss(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();

            ModelAndView modelAndView = new ModelAndView();

            InputStream fis = new FileInputStream(data_path + "facilities.xlsx");
            Workbook workbook = null;
            Sheet sheet;
            try {
                workbook = new XSSFWorkbook(fis);
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(fis);
            }
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {

                sheet = workbook.getSheetAt(s);
                int totalRowNum = sheet.getLastRowNum();
                System.out.println("current table total: " + totalRowNum + " line");
                int headerRown = 2;
                Row headerRow = sheet.getRow(headerRown);
                Row headerTitle = sheet.getRow(3);
                Row superheaderRow = sheet.getRow(1);
                String elementname = headerRow.getCell(0).getStringCellValue();
                System.out.println(elementname);
                for (int x = 1; x <= totalRowNum; x++) {
                    Row dataRow = sheet.getRow(x);
                    String country = dataRow.getCell(0).getStringCellValue();
                    String program = dataRow.getCell(1).getStringCellValue();
                    String county = dataRow.getCell(2).getStringCellValue();
                    String subcounty = dataRow.getCell(3).getStringCellValue();
                    String ward = dataRow.getCell(4).getStringCellValue();
                    String facility = dataRow.getCell(5).getStringCellValue();
                    String amep_org = dataRow.getCell(6).getStringCellValue();
                    String nmflcode = String.valueOf(dataRow.getCell(7).getNumericCellValue());
                    String emr = dataRow.getCell(8).getStringCellValue();
                    String owner = dataRow.getCell(9).getStringCellValue();

                    String mflcode = nmflcode.substring(0, nmflcode.length() - 2);
                    System.out.println(
                            "Row" + x + " Facility " + facility + " mfclode " + nmflcode + " nmflcode " + mflcode);
                    Facilities facilities = facilitiesService.getByMFLCODE("nmflcode");
                    if (facilities == null) {
                        Facilities f = new Facilities();
                        UUID uuid = UUID.randomUUID();
                        f.setUuid(String.valueOf(uuid));
                        f.setCountry(country);
                        f.setCounty(county);
                        f.setEmr(emr);
                        f.setPartner(program);
                        f.setStatus(1);
                        f.setFacilityname(facility);
                        f.setSubcounty(subcounty);
                        f.setOwner(owner);
                        f.setWard(ward);
                        f.setOrg_unit(amep_org);
                        f.setMflcode(mflcode);
                        facilitiesService.save(f);
                    }

                }
            }
            modelAndView.setViewName("dashboard");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }

    @RequestMapping(value = "/process_rridata", method = RequestMethod.GET)
    public ModelAndView process_rridata(HttpSession session) throws IOException {

        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            User userdetails = (User) session.getAttribute("user");
            Date nowDate = new Date();

            ModelAndView modelAndView = new ModelAndView();

            InputStream fis = new FileInputStream(data_path + "rri_data.xlsx");
            Workbook workbook = null;
            Sheet sheet;
            try {
                workbook = new XSSFWorkbook(fis);
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(fis);
            }
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {

                sheet = workbook.getSheetAt(s);
                int totalRowNum = sheet.getLastRowNum();
                System.out.println("current table total: " + totalRowNum + " line");

                for (int x = 1; x <= totalRowNum; x++) {

                    Row dataRow = sheet.getRow(x);
                    String nmflcode = String.valueOf(dataRow.getCell(0).getNumericCellValue());
                    String datatype = String.valueOf(dataRow.getCell(1).getStringCellValue());
                    double tst = 0.0;
                    double pos = 0.0;
                    double hstlink = 0.0;
                    double prepnewsurge = 0.0;
                    double newpbfw = 0.0;
                    double opdworkload = 0.0;
                    double noclientsscreened = 0.0;
                    double identifiedsgbv = 0.0;
                    double identifiedphem = 0.0;
                    double bookedsurge = 0.0;
                    double honouredsurge = 0.0;
                    double scheduledvisits = 0.0;
                    double keptappointment = 0.0;
                    double cameearly = 0.0;
                    double missedappointment = 0.0;
                    double bringbacksurge = 0.0;
                    double broughtbacksurge = 0.0;
                    double targettobringback = 0.0;
                    double numberbroughtback = 0.0;
                    double viralloaddone = 0.0;
                    double newancvisits = 0.0;
                    double kpsatanc1 = 0.0;
                    double testedatanc1 = 0.0;
                    double syphilisatanc1hivpos = 0.0;
                    double syphilisat1stanc = 0.0;
                    double eligibleforvltest = 0.0;
                    double vltestsdone = 0.0;
                    double initialtestslessthan8wks = 0.0;
                    double initialtests8wksto12months = 0.0;
                    double totaleid = 0.0;
                    double eidpos = 0.0;
                    double bringbacktarget = 0.0;
                    double broughtbackamongiit = 0.0;
                    double determinestockathand = 0.0;
                    double responsestockathand = 0.0;
                    double duokitathand = 0.0;
                    double dbsfilterpapers = 0.0;
                    double cxcascreened = 0.0;
                    double cxcascreenedpos = 0.0;
                    double cxcareceivedtreatment = 0.0;
                    double cxcasuspected = 0.0;
                    double currneverontpt = 0.0;
                    double screenednegfortb = 0.0;
                    double startedtpttxnew = 0.0;
                    double startedtpttxcurr = 0.0;
                    double startedtpt = 0.0;
                    double daily1st95childrentested = 0.0;
                    double daily2ndand3rd95cacxtargetedwra = 0.0;
                    double daily2ndand3rd95eligibleforvltest = 0.0;
                    double daily2ndand3rd95hpv10to14yrseligible = 0.0;
                    double daily2ndand3rd95hpv10to14yrs95vaccinated = 0.0;
                    double daily2ndand3rd95newlyiit = 0.0;
                    double daily2ndand3rd95tbcccscreened = 0.0;
                    double daily2ndand3rd95tbcccworkload = 0.0;
                    double daily2ndand3rd95tbopdscreened = 0.0;
                    double daily2ndand3rd95tbopdworkload = 0.0;
                    double otherhivtests = 0.0;
                    double knownstatuskpplustested = 0.0;
                    double pmtctstattd = 0.0;
                    double tptcummulative = 0.0;
                    double completedtpt = 0.0;
                    double completedtpttxnew = 0.0;
                    double completedtpttxcurr = 0.0;
                    double missedhivtestingtarget = 0.0;
                    double reachedandaccounted = 0.0;
                    double newiitclients = 0.0;
                    double tptuptakeeligiblefortpt = 0.0;
                    double tptuptakestartedontpt = 0.0;
                    double prepnewagyw = 0.0;
                    double week = 0.0;
                    double month = 0.0;

                    Cell tstcell = dataRow.getCell(2);

                    if (tstcell == null) {
                        tst = 0.0;
                    } else {
                        tst = dataRow.getCell(2).getNumericCellValue();
                    }
                    Cell poscell = dataRow.getCell(3);

                    if (poscell == null) {
                        pos = 0.0;
                    } else {
                        pos = dataRow.getCell(3).getNumericCellValue();
                        // pos = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,
                        // String.valueOf(dataRow.getCell(3).getNumericCellValue()).length() - 2);

                    }
                    Cell hstlinkcell = dataRow.getCell(4);

                    if (hstlinkcell == null) {
                        hstlink = 0.0;
                    } else {
                        hstlink = dataRow.getCell(4).getNumericCellValue();
                    }

                    Cell prepnewsurgecell = dataRow.getCell(5);

                    if (prepnewsurgecell == null) {
                        prepnewsurge = 0.0;
                    } else {
                        prepnewsurge = dataRow.getCell(5).getNumericCellValue();
                    }

                    Cell newpbfwcell = dataRow.getCell(6);

                    if (newpbfwcell == null) {
                        newpbfw = 0.0;
                    } else {
                        newpbfw = dataRow.getCell(6).getNumericCellValue();
                    }

                    Cell opdworkloadcell = dataRow.getCell(7);

                    if (opdworkloadcell == null) {
                        opdworkload = 0.0;
                    } else {
                        opdworkload = dataRow.getCell(7).getNumericCellValue();
                    }

                    Cell noclientsscreenedcell = dataRow.getCell(8);

                    if (noclientsscreenedcell == null) {
                        noclientsscreened = 0.0;
                    } else {
                        noclientsscreened = dataRow.getCell(8).getNumericCellValue();
                    }

                    Cell identifiedsgbvcell = dataRow.getCell(9);

                    if (identifiedsgbvcell == null) {
                        identifiedsgbv = 0.0;
                    } else {
                        identifiedsgbv = dataRow.getCell(9).getNumericCellValue();
                    }

                    Cell identifiedphemcell = dataRow.getCell(10);

                    if (identifiedphemcell == null) {
                        identifiedphem = 0.0;
                    } else {
                        identifiedphem = dataRow.getCell(10).getNumericCellValue();
                    }

                    Cell bookedsurgecell = dataRow.getCell(11);

                    if (bookedsurgecell == null) {
                        bookedsurge = 0.0;
                    } else {
                        bookedsurge = dataRow.getCell(11).getNumericCellValue();
                    }

                    Cell honouredsurgecell = dataRow.getCell(12);

                    if (honouredsurgecell == null) {
                        honouredsurge = 0.0;
                    } else {
                        honouredsurge = dataRow.getCell(12).getNumericCellValue();
                    }

                    Cell scheduledvisitscell = dataRow.getCell(13);

                    if (scheduledvisitscell == null) {
                        scheduledvisits = 0.0;
                    } else {
                        scheduledvisits = dataRow.getCell(13).getNumericCellValue();
                    }

                    Cell keptappointmentcell = dataRow.getCell(14);

                    if (keptappointmentcell == null) {
                        keptappointment = 0.0;
                    } else {
                        keptappointment = dataRow.getCell(14).getNumericCellValue();
                    }

                    Cell cameearlycell = dataRow.getCell(15);

                    if (cameearlycell == null) {
                        cameearly = 0.0;
                    } else {
                        cameearly = dataRow.getCell(15).getNumericCellValue();
                    }

                    Cell missedappointmentcell = dataRow.getCell(16);

                    if (missedappointmentcell == null) {
                        missedappointment = 0.0;
                    } else {
                        missedappointment = dataRow.getCell(16).getNumericCellValue();
                    }

                    Cell bringbacksurgecell = dataRow.getCell(17);

                    if (bringbacksurgecell == null) {
                        bringbacksurge = 0.0;
                    } else {
                        bringbacksurge = dataRow.getCell(17).getNumericCellValue();
                    }

                    Cell broughtbacksurgecell = dataRow.getCell(18);

                    if (broughtbacksurgecell == null) {
                        broughtbacksurge = 0.0;
                    } else {
                        broughtbacksurge = dataRow.getCell(18).getNumericCellValue();
                    }

                    Cell targettobringbackcell = dataRow.getCell(19);

                    if (targettobringbackcell == null) {
                        targettobringback = 0.0;
                    } else {
                        targettobringback = dataRow.getCell(19).getNumericCellValue();
                    }

                    Cell numberbroughtbackcell = dataRow.getCell(20);

                    if (numberbroughtbackcell == null) {
                        numberbroughtback = 0.0;
                    } else {
                        numberbroughtback = dataRow.getCell(20).getNumericCellValue();
                    }

                    Cell viralloaddonecell = dataRow.getCell(21);

                    if (viralloaddonecell == null) {
                        viralloaddone = 0.0;
                    } else {
                        viralloaddone = dataRow.getCell(21).getNumericCellValue();
                    }

                    Cell newancvisitscell = dataRow.getCell(22);

                    if (newancvisitscell == null) {
                        newancvisits = 0.0;
                    } else {
                        newancvisits = dataRow.getCell(22).getNumericCellValue();
                    }

                    Cell kpsatanc1cell = dataRow.getCell(23);

                    if (kpsatanc1cell == null) {
                        kpsatanc1 = 0.0;
                    } else {
                        kpsatanc1 = dataRow.getCell(23).getNumericCellValue();
                    }

                    Cell testedatanc1cell = dataRow.getCell(24);

                    if (testedatanc1cell == null) {
                        testedatanc1 = 0.0;
                    } else {
                        testedatanc1 = dataRow.getCell(24).getNumericCellValue();
                    }

                    Cell syphilisatanc1hivposcell = dataRow.getCell(25);

                    if (syphilisatanc1hivposcell == null) {
                        syphilisatanc1hivpos = 0.0;
                    } else {
                        syphilisatanc1hivpos = dataRow.getCell(25).getNumericCellValue();
                    }

                    Cell syphilisat1stanccell = dataRow.getCell(26);

                    if (syphilisat1stanccell == null) {
                        syphilisat1stanc = 0.0;
                    } else {
                        syphilisat1stanc = dataRow.getCell(26).getNumericCellValue();
                    }

                    Cell eligibleforvltestcell = dataRow.getCell(27);

                    if (eligibleforvltestcell == null) {
                        eligibleforvltest = 0.0;
                    } else {
                        eligibleforvltest = dataRow.getCell(27).getNumericCellValue();
                    }

                    Cell vltestsdonecell = dataRow.getCell(28);

                    if (vltestsdonecell == null) {
                        vltestsdone = 0.0;
                    } else {
                        vltestsdone = dataRow.getCell(28).getNumericCellValue();
                    }

                    Cell initialtestslessthan8wkscell = dataRow.getCell(29);

                    if (initialtestslessthan8wkscell == null) {
                        initialtestslessthan8wks = 0.0;
                    } else {
                        initialtestslessthan8wks = dataRow.getCell(29).getNumericCellValue();
                    }

                    Cell initialtests8wksto12monthscell = dataRow.getCell(30);

                    if (initialtests8wksto12monthscell == null) {
                        initialtests8wksto12months = 0.0;
                    } else {
                        initialtests8wksto12months = dataRow.getCell(30).getNumericCellValue();
                    }

                    Cell totaleidcell = dataRow.getCell(31);

                    if (totaleidcell == null) {
                        totaleid = 0.0;
                    } else {
                        totaleid = dataRow.getCell(31).getNumericCellValue();
                    }

                    Cell eidposcell = dataRow.getCell(32);

                    if (eidposcell == null) {
                        eidpos = 0.0;
                    } else {
                        eidpos = dataRow.getCell(32).getNumericCellValue();
                    }

                    Cell bringbacktargetcell = dataRow.getCell(33);

                    if (bringbacktargetcell == null) {
                        bringbacktarget = 0.0;
                    } else {
                        bringbacktarget = dataRow.getCell(33).getNumericCellValue();
                    }

                    Cell broughtbackamongiitcell = dataRow.getCell(34);

                    if (broughtbackamongiitcell == null) {
                        broughtbackamongiit = 0.0;
                    } else {
                        broughtbackamongiit = dataRow.getCell(34).getNumericCellValue();
                    }

                    Cell determinestockathandcell = dataRow.getCell(35);

                    if (determinestockathandcell == null) {
                        determinestockathand = 0.0;
                    } else {
                        determinestockathand = dataRow.getCell(35).getNumericCellValue();
                    }

                    Cell responsestockathandcell = dataRow.getCell(36);

                    if (responsestockathandcell == null) {
                        responsestockathand = 0.0;
                    } else {
                        responsestockathand = dataRow.getCell(36).getNumericCellValue();
                    }

                    Cell duokitathandcell = dataRow.getCell(37);

                    if (duokitathandcell == null) {
                        duokitathand = 0.0;
                    } else {
                        duokitathand = dataRow.getCell(37).getNumericCellValue();
                    }

                    Cell dbsfilterpaperscell = dataRow.getCell(38);

                    if (dbsfilterpaperscell == null) {
                        dbsfilterpapers = 0.0;
                    } else {
                        dbsfilterpapers = dataRow.getCell(38).getNumericCellValue();
                    }

                    Cell cxcascreenedcell = dataRow.getCell(39);

                    if (cxcascreenedcell == null) {
                        cxcascreened = 0.0;
                    } else {
                        cxcascreened = dataRow.getCell(39).getNumericCellValue();
                    }

                    Cell cxcascreenedposcell = dataRow.getCell(40);

                    if (cxcascreenedposcell == null) {
                        cxcascreenedpos = 0.0;
                    } else {
                        cxcascreenedpos = dataRow.getCell(40).getNumericCellValue();
                    }

                    Cell cxcareceivedtreatmentcell = dataRow.getCell(41);

                    if (cxcareceivedtreatmentcell == null) {
                        cxcareceivedtreatment = 0.0;
                    } else {
                        cxcareceivedtreatment = dataRow.getCell(41).getNumericCellValue();
                    }

                    Cell cxcasuspectedcell = dataRow.getCell(42);

                    if (cxcasuspectedcell == null) {
                        cxcasuspected = 0.0;
                    } else {
                        cxcasuspected = dataRow.getCell(42).getNumericCellValue();
                    }

                    Cell currneverontptcell = dataRow.getCell(43);

                    if (currneverontptcell == null) {
                        currneverontpt = 0.0;
                    } else {
                        currneverontpt = dataRow.getCell(43).getNumericCellValue();
                    }

                    Cell screenednegfortbcell = dataRow.getCell(44);

                    if (screenednegfortbcell == null) {
                        screenednegfortb = 0.0;
                    } else {
                        screenednegfortb = dataRow.getCell(44).getNumericCellValue();
                    }

                    Cell startedtpttxnewcell = dataRow.getCell(45);

                    if (startedtpttxnewcell == null) {
                        startedtpttxnew = 0.0;
                    } else {
                        startedtpttxnew = dataRow.getCell(45).getNumericCellValue();
                    }

                    Cell startedtpttxcurrcell = dataRow.getCell(46);

                    if (startedtpttxcurrcell == null) {
                        startedtpttxcurr = 0.0;
                    } else {
                        startedtpttxcurr = dataRow.getCell(46).getNumericCellValue();
                    }

                    Cell startedtptcell = dataRow.getCell(47);

                    if (startedtptcell == null) {
                        startedtpt = 0.0;
                    } else {
                        startedtpt = dataRow.getCell(47).getNumericCellValue();
                    }

                    Cell daily1st95childrentestedcell = dataRow.getCell(48);

                    if (daily1st95childrentestedcell == null) {
                        daily1st95childrentested = 0.0;
                    } else {
                        daily1st95childrentested = dataRow.getCell(48).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95cacxtargetedwracell = dataRow.getCell(49);

                    if (daily2ndand3rd95cacxtargetedwracell == null) {
                        daily2ndand3rd95cacxtargetedwra = 0.0;
                    } else {
                        daily2ndand3rd95cacxtargetedwra = dataRow.getCell(49).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95eligibleforvltestcell = dataRow.getCell(50);

                    if (daily2ndand3rd95eligibleforvltestcell == null) {
                        daily2ndand3rd95eligibleforvltest = 0.0;
                    } else {
                        daily2ndand3rd95eligibleforvltest = dataRow.getCell(50).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95hpv10to14yrseligiblecell = dataRow.getCell(51);

                    if (daily2ndand3rd95hpv10to14yrseligiblecell == null) {
                        daily2ndand3rd95hpv10to14yrseligible = 0.0;
                    } else {
                        daily2ndand3rd95hpv10to14yrseligible = dataRow.getCell(51).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95hpv10to14yrs95vaccinatedcell = dataRow.getCell(52);

                    if (daily2ndand3rd95hpv10to14yrs95vaccinatedcell == null) {
                        daily2ndand3rd95hpv10to14yrs95vaccinated = 0.0;
                    } else {
                        daily2ndand3rd95hpv10to14yrs95vaccinated = dataRow.getCell(52).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95newlyiitcell = dataRow.getCell(53);

                    if (daily2ndand3rd95newlyiitcell == null) {
                        daily2ndand3rd95newlyiit = 0.0;
                    } else {
                        daily2ndand3rd95newlyiit = dataRow.getCell(53).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbcccscreenedcell = dataRow.getCell(54);

                    if (daily2ndand3rd95tbcccscreenedcell == null) {
                        daily2ndand3rd95tbcccscreened = 0.0;
                    } else {
                        daily2ndand3rd95tbcccscreened = dataRow.getCell(54).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbcccworkloadcell = dataRow.getCell(55);

                    if (daily2ndand3rd95tbcccworkloadcell == null) {
                        daily2ndand3rd95tbcccworkload = 0.0;
                    } else {
                        daily2ndand3rd95tbcccworkload = dataRow.getCell(55).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbopdscreenedcell = dataRow.getCell(56);

                    if (daily2ndand3rd95tbopdscreenedcell == null) {
                        daily2ndand3rd95tbopdscreened = 0.0;
                    } else {
                        daily2ndand3rd95tbopdscreened = dataRow.getCell(56).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbopdworkloadcell = dataRow.getCell(57);

                    if (daily2ndand3rd95tbopdworkloadcell == null) {
                        daily2ndand3rd95tbopdworkload = 0.0;
                    } else {
                        daily2ndand3rd95tbopdworkload = dataRow.getCell(57).getNumericCellValue();
                    }

                    Cell otherhivtestscell = dataRow.getCell(58);

                    if (otherhivtestscell == null) {
                        otherhivtests = 0.0;
                    } else {
                        otherhivtests = dataRow.getCell(58).getNumericCellValue();
                    }

                    Cell knownstatuskpplustestedcell = dataRow.getCell(59);

                    if (knownstatuskpplustestedcell == null) {
                        knownstatuskpplustested = 0.0;
                    } else {
                        knownstatuskpplustested = dataRow.getCell(59).getNumericCellValue();
                    }

                    Cell pmtctstattdcell = dataRow.getCell(60);

                    if (pmtctstattdcell == null) {
                        pmtctstattd = 0.0;
                    } else {
                        pmtctstattd = dataRow.getCell(60).getNumericCellValue();
                    }

                    Cell tptcummulativecell = dataRow.getCell(61);

                    if (tptcummulativecell == null) {
                        tptcummulative = 0.0;
                    } else {
                        tptcummulative = dataRow.getCell(61).getNumericCellValue();
                    }

                    Cell completedtptcell = dataRow.getCell(62);

                    if (completedtptcell == null) {
                        completedtpt = 0.0;
                    } else {
                        completedtpt = dataRow.getCell(62).getNumericCellValue();
                    }

                    Cell completedtpttxnewcell = dataRow.getCell(63);

                    if (completedtpttxnewcell == null) {
                        completedtpttxnew = 0.0;
                    } else {
                        completedtpttxnew = dataRow.getCell(63).getNumericCellValue();
                    }

                    Cell completedtpttxcurrcell = dataRow.getCell(64);

                    if (completedtpttxcurrcell == null) {
                        completedtpttxcurr = 0.0;
                    } else {
                        completedtpttxcurr = dataRow.getCell(64).getNumericCellValue();
                    }

                    Cell missedhivtestingtargetcell = dataRow.getCell(65);

                    if (missedhivtestingtargetcell == null) {
                        missedhivtestingtarget = 0.0;
                    } else {
                        missedhivtestingtarget = dataRow.getCell(65).getNumericCellValue();
                    }

                    Cell reachedandaccountedcell = dataRow.getCell(66);

                    if (reachedandaccountedcell == null) {
                        reachedandaccounted = 0.0;
                    } else {
                        reachedandaccounted = dataRow.getCell(66).getNumericCellValue();
                    }

                    Cell newiitclientscell = dataRow.getCell(67);

                    if (newiitclientscell == null) {
                        newiitclients = 0.0;
                    } else {
                        newiitclients = dataRow.getCell(67).getNumericCellValue();
                    }

                    Cell tptuptakeeligiblefortptcell = dataRow.getCell(68);

                    if (tptuptakeeligiblefortptcell == null) {
                        tptuptakeeligiblefortpt = 0.0;
                    } else {
                        tptuptakeeligiblefortpt = dataRow.getCell(68).getNumericCellValue();
                    }

                    Cell tptuptakestartedontptcell = dataRow.getCell(69);

                    if (tptuptakestartedontptcell == null) {
                        tptuptakestartedontpt = 0.0;
                    } else {
                        tptuptakestartedontpt = dataRow.getCell(69).getNumericCellValue();
                    }

                    Cell prepnewagywcell = dataRow.getCell(70);

                    if (prepnewagywcell == null) {
                        prepnewagyw = 0.0;
                    } else {
                        prepnewagyw = dataRow.getCell(70).getNumericCellValue();
                    }

                    Cell weekcell = dataRow.getCell(71);

                    if (weekcell == null) {
                        week = 0.0;
                    } else {
                        week = dataRow.getCell(71).getNumericCellValue();
                    }

                    Cell monthcell = dataRow.getCell(72);

                    if (monthcell == null) {
                        month = 0.0;
                    } else {
                        month = dataRow.getCell(72).getNumericCellValue();
                    }

                    String mflcode = nmflcode.substring(0, nmflcode.length() - 2);

                    System.out.println("Row" + x + " mfclode " + nmflcode + " nmflcode " + mflcode + " TST " + tst
                            + " POS " + pos + " LINK " + hstlink + " NEW_SURGE " + prepnewsurge);
                    UUID uuid = UUID.randomUUID();
                    RRI rri = new RRI();
                    rri.setCategory(datatype);
                    rri.setUuid(String.valueOf(uuid));
                    rri.setHst_pos((int) pos);
                    rri.setHst_tst((int) tst);
                    rri.setHst_link((int) hstlink);
                    rri.setPrep_new_surge((int) prepnewsurge);
                    rri.setDaily_prep_new_pbfw((int) newpbfw);
                    rri.setFacility_opd_work_load((int) opdworkload);
                    rri.setNumber_of_clients_screened((int) clientsscreened);
                    rri.setDaily_gbv_cases_identified_sgbv((int) identifiedsgbv);
                    rri.setDaily_gbv_cases_identified_physical_emotional((int) identifiedphem);
                    rri.setSecond_visits_for_new_art_clients_booked_surge((int) bookedsurge);
                    rri.setSecond_visits_for_new_art_clients_honoured_surge((int) honouredsurge);
                    rri.setDaily_scheduled_visits((int) scheduledvisits);
                    rri.setDaily_no_booked_kept_appointment_ka((int) keptappointment);
                    rri.setDaily_no_booked_came_early_ce((int) cameearly);
                    rri.setDaily_no_booked_missed_appointment_ma((int) missedappointment);
                    rri.setClients_disengagement_targeted_to_bring_back_surge((int) bringbacksurge);
                    rri.setClients_disengagement_no_brought_back_surge((int) broughtbacksurge);
                    rri.setIit_target_to_bring_back((int) targettobringback);
                    rri.setIit_number_brought_back((int) numberbroughtback);
                    rri.setViral_load_done((int) viralloaddone);
                    rri.setDaily_pmtct_stat_new_anc_visits((int) newancvisits);
                    rri.setDaily_pmtct_stat_kps_at_anc1((int) kpsatanc1);
                    rri.setDaily_pmtct_stat_tested_at_anc1((int) testedatanc1 );
                    rri.setDaily_pmtct_stat_and_syphilis_at_anc1_hiv_pos((int) syphilisatanc1hivpos);
                    rri.setDaily_tested_for_syphilis_at_1st_anc((int) syphilisat1stanc);
                    rri.setDaily_pmtct_vl_eligible_for_vl_test((int) eligibleforvltest);
                    rri.setDaily_pmtct_vl_vl_tests_done((int) vltestsdone);
                    rri.setEid_eid_initial_tests_less_than_8_weeks((int) initialtestslessthan8wks);
                    rri.setEid_eid_initial_tests_8_weeks_to_12_weeks((int) initialtests8wksto12months);
                    rri.setTotal_eid((int) totaleid);
                    rri.setEid_eid_positive((int) eidpos);
                    rri.setDaily_pmtct_iit_no_to_bring_back_target((int) bringbacktarget);
                    rri.setDaily_pmtct_iit_no_brought_back_among_iit((int) broughtbackamongiit);
                    rri.setDaily_pmtct_determine_stock_at_hand((int) determinestockathand);
                    rri.setDaily_pmtct_first_response_stock_at_hand((int) responsestockathand);
                    rri.setDaily_pmtct_duo_kits_at_hand((int) duokitathand);
                    rri.setDaily_pmtct_dbs_filter_papers((int) dbsfilterpapers);
                    rri.setCxca_cxsa_screened((int) cxcascreened);
                    rri.setCxca_cxca_screened_positive((int) cxcascreenedpos);
                    rri.setCxca_cxca_received_treatment((int) cxcareceivedtreatment);
                    rri.setCxca_cxca_suspected((int) cxcasuspected);
                    rri.setDaily_tpt_tx_curr_never_on_tpt((int) currneverontpt);
                    rri.setDaily_tpt_no_screened_neg_for_tb((int) screenednegfortb );
                    rri.setDaily_tpt_started_tpt_tx_new((int) startedtpttxnew);
                    rri.setDaily_tpt_started_tpt_tx_curr((int) startedtpttxcurr);
                    rri.setDaily_tpt_started_tpt((int) startedtpt);
                    rri.setDaily_1st_95_index_testing_children_tested((int) daily1st95childrentested);
                    rri.setDaily_2nd_and_3rd_95_cacx_targeted_wra((int) daily2ndand3rd95cacxtargetedwra);
                    rri.setDaily_2nd_and_3rd_95_eligible_for_vl_test((int) daily2ndand3rd95eligibleforvltest);
                    rri.setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible((int) daily2ndand3rd95hpv10to14yrseligible);
                    rri.setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated((int) daily2ndand3rd95hpv10to14yrs95vaccinated);
                    rri.setDaily_2nd_and_3rd_95_iit_no_newly_iit((int) daily2ndand3rd95newlyiit);
                    rri.setDaily_2nd_and_3rd_95_tb_ccc_no_screened((int) daily2ndand3rd95tbcccscreened);
                    rri.setDaily_2nd_and_3rd_95_tb_ccc_workload((int) daily2ndand3rd95tbcccworkload);
                    rri.setDaily_2nd_and_3rd_95_tb_opd_no_screened((int) daily2ndand3rd95tbopdscreened);
                    rri.setDaily_2nd_and_3rd_95_tb_opd_workload((int) daily2ndand3rd95tbopdworkload);
                    rri.setDaily_other_hiv_tests((int) otherhivtests);
                    rri.setDaily_pmtct_stat_known_status_kp_tested((int) knownstatuskpplustested);
                    rri.setDaily_pmtct_stat_pmtct_stat_d((int) pmtctstattd);
                    rri.setDaily_tpt_active_on_tpt_cummulative((int) tptcummulative);
                    rri.setDaily_tpt_completed_tpt((int) completedtpt);
                    rri.setDaily_tpt_completed_tpt_tx_new((int) completedtpttxnew);
                    rri.setDaily_tpt_completed_tpt_tx_current((int) completedtpttxcurr);
                    rri.setDaily_pmtct_missed_hiv_testing_target((int) missedhivtestingtarget);
                    rri.setDaily_pmtct_reached_and_accounted((int) reachedandaccounted);
                    rri.setDaily_pmtct_iit_new_iit_clients((int) newiitclients);
                    rri.setDaily_pmtct_tpt_uptake_eligible_for_tpt((int) tptuptakeeligiblefortpt);
                    rri.setDaily_pmtct_tpt_uptake_started_on_tpt((int) tptuptakestartedontpt);
                    rri.setDaily_prep_new_agyw((int) prepnewagyw);
                    rri.setWeek((int) week);
                    rri.setMonth((int) month);
                    rri.setCreated_by(userdetails.getId());
                    rri.setCreated_on(nowDate);
                    rri.setPrep_new_pbfw((int) newpbfw);
                    rri.setOpd_workload((int) opdworkload);
                    rri.setNo_of_clients_screened_hp((int) noclientsscreened);
                    rri.setDaily_gbv_cases_identified_sgbv((int) identifiedsgbv);
                    rri.setDaily_gbv_cases_identified_physical_or_emotional((int) identifiedphem);
                    rri.setSecond_visits_for_new_art_clients_booked_surge((int) bookedsurge);
                    rri.setSecond_visits_for_new_art_clients_honoured_surge((int) honouredsurge);
                    rri.setDaily_scheduled_visits((int) scheduledvisits);
                    rri.setDaily_no_booked_kept_appointment_ka((int) keptappointment);
                    rri.setDaily_no_booked_came_early_ce((int) cameearly);
                    rri.setDaily_no_booked_missed_appointment_ma((int) missedappointment);
                    rri.setClients_disengagement_targeted_to_bring_back_surge((int) bringbacksurge);
                    rri.setClients_disengagement_no_brought_back_surge((int) broughtbacksurge);
                    rri.setIit_target_to_bring_back((int) targettobringback);
                    rri.setIit_no_brought_back((int) numberbroughtback);
                    rri.setViral_load_done((int) viralloaddone);
                    rri.setDaily_pmtct_stat_new_anc_visits((int) newancvisits);
                    rri.setDaily_pmtct_stat_kps_at_anc1((int) kpsatanc1);
                    rri.setDaily_pmtct_stat_tested_at_anc1((int) testedatanc1);
                    rri.setDaily_pmtct_stat_and_syphillies_at_anc1_hiv_pos((int) syphilisatanc1hivpos);
                    rri.setDaily_tested_for_syphilis_at_1st_anc((int) syphilisat1stanc);
                    rri.setDaily_pmtct_vl_eligible_for_vl_test((int) eligibleforvltest);
                    rri.setDaily_pmtct_vl_vl_tests_done((int) vltestsdone);
                    rri.setEid_eid_initial_tests_less_than_8_weeks((int) initialtestslessthan8wks);
                    rri.setEid_eid_initial_tests_8_weeks_to_12_months((int) initialtests8wksto12months);
                    rri.setTotal_eid((int) totaleid);
                    rri.setEid_eid_pos((int) eidpos);
                    rri.setDaily_pmtct_iit_no_to_bring_back_target((int) bringbacktarget);
                    rri.setDaily_pmtct_iit_no_brought_back_among_iit((int) broughtbackamongiit);
                    rri.setDaily_pmtct_determine_stock_at_hand((int) determinestockathand);
                    rri.setDaily_pmtct_first_response_stock_at_hand((int) responsestockathand);
                    rri.setDaily_pmtct_duo_kits_at_hand((int) duokitathand);
                    rri.setDaily_pmtct_dbs_filter_papers((int) dbsfilterpapers);
                    rri.setCxca_cxca_screened((int) cxcascreened);
                    rri.setCxca_cxca_screened_pos((int) cxcascreenedpos);
                    rri.setCxca_cxca_received_treatment((int) cxcareceivedtreatment);
                    rri.setCxca_cxca_suspected((int) cxcasuspected);
                    rri.setDaily_tpt_tx_curr_never_on_tpt((int) currneverontpt);
                    rri.setDaily_tpt_no_screened_neg_for_tb((int) screenednegfortb);
                    rri.setDaily_tpt_started_tpt_tx_new((int) startedtpttxnew);
                    rri.setDaily_tpt_started_tpt_tx_curr((int) startedtpttxcurr);
                    rri.setDaily_tpt_started_tpt((int) startedtpt);
                    rri.setDaily_1st_95_index_testing_children_tested((int) daily1st95childrentested);
                    rri.setDaily_2nd_and_3rd_95_cacx_targeted_wra((int) daily2ndand3rd95cacxtargetedwra);
                    rri.setDaily_2nd_and_3rd_95_eligible_for_vl_test((int) daily2ndand3rd95eligibleforvltest);
                    rri.setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_eligible((int) daily2ndand3rd95hpv10to14yrseligible);
                    rri.setDaily_2nd_and_3rd_95_hpv_10_to_14_yrs_vaccinated(
                            (int) daily2ndand3rd95hpv10to14yrs95vaccinated);
                    rri.setDaily_2nd_and_3rd_95_no_newly_iit((int) daily2ndand3rd95newlyiit);
                    rri.setDaily_2nd_and_3rd_95_tb_ccc_no_screened((int) daily2ndand3rd95tbcccscreened);
                    rri.setDaily_2nd_and_3rd_95_tb_ccc_workload((int) daily2ndand3rd95tbcccworkload);
                    rri.setDaily_2nd_and_3rd_95_tb_opd_no_screened((int) daily2ndand3rd95tbopdscreened);
                    rri.setDaily_2nd_and_3rd_95_tb_opd_workload((int) daily2ndand3rd95tbopdworkload);
                    rri.setDaily_other_hiv_tests((int) otherhivtests);
                    rri.setDaily_pmtct_stat_known_status_kp_plus_tested((int) knownstatuskpplustested);
                    rri.setDaily_pmtct_stat_pmtct_stat_d((int) pmtctstattd);
                    rri.setDaily_tpt_active_on_tpt_cummulative((int) tptcummulative);
                    rri.setDaily_tpt_completed_tpt((int) completedtpt);
                    rri.setDaily_tpt_completed_tpt_tx_new((int) completedtpttxnew);
                    rri.setDaily_tpt_completed_tpt_tx_curr((int) completedtpttxcurr);
                    rri.setDaily_pmtct_missed_hiv_testing_target((int) missedhivtestingtarget);
                    rri.setDaily_pmtct_reached_and_accounted((int) reachedandaccounted);
                    rri.setDaily_pmtct_iit_new_iit_clients((int) newiitclients);
                    rri.setDaily_pmtct_tpt_uptake_eligible_for_tpt((int) tptuptakeeligiblefortpt);
                    rri.setDaily_pmtct_tpt_uptake_started_on_tpt((int) tptuptakestartedontpt);
                    rri.setDaily_prep_new_agyw((int) prepnewagyw);
                    rri.setWeek((int) week);
                    rri.setMonth((int) month);
                    rriService.save(rri);

                }
            }
            modelAndView.setViewName("dashboard");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }

}
