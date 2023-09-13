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
                    double clientsscreened = 0.0;
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

                    Cell clientsscreenedcell = dataRow.getCell(7);

                    if (clientsscreenedcell == null) {
                        clientsscreened = 0.0;
                    } else {
                        newpbfw = dataRow.getCell(7).getNumericCellValue();
                    }

                    Cell identifiedsgbvcell = dataRow.getCell(8);

                    if (identifiedsgbvcell == null) {
                        identifiedsgbv = 0.0;
                    } else {
                        identifiedsgbv = dataRow.getCell(8).getNumericCellValue();
                    }

                    Cell identifiedphemcell = dataRow.getCell(9);

                    if (identifiedphemcell == null) {
                        identifiedphem = 0.0;
                    } else {
                        identifiedphem = dataRow.getCell(9).getNumericCellValue();
                    }

                    Cell bookedsurgecell = dataRow.getCell(10);

                    if (bookedsurgecell == null) {
                        bookedsurge = 0.0;
                    } else {
                        bookedsurge = dataRow.getCell(10).getNumericCellValue();
                    }

                    Cell honouredsurgecell = dataRow.getCell(11);

                    if (honouredsurgecell == null) {
                        honouredsurge = 0.0;
                    } else {
                        honouredsurge = dataRow.getCell(11).getNumericCellValue();
                    }

                    Cell scheduledvisitscell = dataRow.getCell(12);

                    if (scheduledvisitscell == null) {
                        scheduledvisits = 0.0;
                    } else {
                        scheduledvisits = dataRow.getCell(12).getNumericCellValue();
                    }

                    Cell keptappointmentcell = dataRow.getCell(13);

                    if (keptappointmentcell == null) {
                        keptappointment = 0.0;
                    } else {
                        keptappointment = dataRow.getCell(13).getNumericCellValue();
                    }

                    Cell cameearlycell = dataRow.getCell(14);

                    if (cameearlycell == null) {
                        cameearly = 0.0;
                    } else {
                        cameearly = dataRow.getCell(14).getNumericCellValue();
                    }

                    Cell missedappointmentcell = dataRow.getCell(15);

                    if (missedappointmentcell == null) {
                        missedappointment = 0.0;
                    } else {
                        missedappointment = dataRow.getCell(15).getNumericCellValue();
                    }

                    Cell bringbacksurgecell = dataRow.getCell(16);

                    if (bringbacksurgecell == null) {
                        bringbacksurge = 0.0;
                    } else {
                        bringbacksurge = dataRow.getCell(16).getNumericCellValue();
                    }

                    Cell broughtbacksurgecell = dataRow.getCell(17);

                    if (broughtbacksurgecell == null) {
                        broughtbacksurge = 0.0;
                    } else {
                        broughtbacksurge = dataRow.getCell(17).getNumericCellValue();
                    }

                    Cell viralloaddonecell = dataRow.getCell(18);

                    if (viralloaddonecell == null) {
                        viralloaddone = 0.0;
                    } else {
                        viralloaddone = dataRow.getCell(18).getNumericCellValue();
                    }

                    Cell newancvisitscell = dataRow.getCell(19);

                    if (newancvisitscell == null) {
                        newancvisits = 0.0;
                    } else {
                        newancvisits = dataRow.getCell(19).getNumericCellValue();
                    }

                    Cell kpsatanc1cell = dataRow.getCell(20);

                    if (kpsatanc1cell == null) {
                        kpsatanc1 = 0.0;
                    } else {
                        kpsatanc1 = dataRow.getCell(20).getNumericCellValue();
                    }

                    Cell testedatanc1cell = dataRow.getCell(21);

                    if (testedatanc1cell == null) {
                        testedatanc1 = 0.0;
                    } else {
                        testedatanc1 = dataRow.getCell(21).getNumericCellValue();
                    }

                    Cell syphilisatanc1hivposcell = dataRow.getCell(22);

                    if (syphilisatanc1hivposcell == null) {
                        syphilisatanc1hivpos = 0.0;
                    } else {
                        syphilisatanc1hivpos = dataRow.getCell(22).getNumericCellValue();
                    }

                    Cell syphilisat1stanccell = dataRow.getCell(23);

                    if (syphilisat1stanccell == null) {
                        syphilisat1stanc = 0.0;
                    } else {
                        syphilisat1stanc = dataRow.getCell(23).getNumericCellValue();
                    }

                    Cell eligibleforvltestcell = dataRow.getCell(24);

                    if (eligibleforvltestcell == null) {
                        eligibleforvltest = 0.0;
                    } else {
                        eligibleforvltest = dataRow.getCell(24).getNumericCellValue();
                    }

                    Cell vltestsdonecell = dataRow.getCell(25);

                    if (vltestsdonecell == null) {
                        vltestsdone = 0.0;
                    } else {
                        vltestsdone = dataRow.getCell(25).getNumericCellValue();
                    }

                    Cell initialtestslessthan8wkscell = dataRow.getCell(26);

                    if (initialtestslessthan8wkscell == null) {
                        initialtestslessthan8wks = 0.0;
                    } else {
                        initialtestslessthan8wks = dataRow.getCell(26).getNumericCellValue();
                    }

                    Cell initialtests8wksto12monthscell = dataRow.getCell(27);

                    if (initialtests8wksto12monthscell == null) {
                        initialtests8wksto12months = 0.0;
                    } else {
                        initialtests8wksto12months = dataRow.getCell(27).getNumericCellValue();
                    }

                    Cell totaleidcell = dataRow.getCell(28);

                    if (totaleidcell == null) {
                        totaleid = 0.0;
                    } else {
                        totaleid = dataRow.getCell(28).getNumericCellValue();
                    }

                    Cell eidposcell = dataRow.getCell(29);

                    if (eidposcell == null) {
                        eidpos = 0.0;
                    } else {
                        eidpos = dataRow.getCell(29).getNumericCellValue();
                    }

                    Cell bringbacktargetcell = dataRow.getCell(30);

                    if (bringbacktargetcell == null) {
                        bringbacktarget = 0.0;
                    } else {
                        bringbacktarget = dataRow.getCell(30).getNumericCellValue();
                    }

                    Cell broughtbackamongiitcell = dataRow.getCell(31);

                    if (broughtbackamongiitcell == null) {
                        broughtbackamongiit = 0.0;
                    } else {
                        broughtbackamongiit = dataRow.getCell(31).getNumericCellValue();
                    }

                    Cell determinestockathandcell = dataRow.getCell(32);

                    if (determinestockathandcell == null) {
                        determinestockathand = 0.0;
                    } else {
                        determinestockathand = dataRow.getCell(32).getNumericCellValue();
                    }

                    Cell responsestockathandcell = dataRow.getCell(33);

                    if (responsestockathandcell == null) {
                        responsestockathand = 0.0;
                    } else {
                        responsestockathand = dataRow.getCell(33).getNumericCellValue();
                    }

                    Cell duokitathandcell = dataRow.getCell(34);

                    if (duokitathandcell == null) {
                        duokitathand = 0.0;
                    } else {
                        duokitathand = dataRow.getCell(34).getNumericCellValue();
                    }

                    Cell dbsfilterpaperscell = dataRow.getCell(35);

                    if (dbsfilterpaperscell == null) {
                        dbsfilterpapers = 0.0;
                    } else {
                        dbsfilterpapers = dataRow.getCell(35).getNumericCellValue();
                    }

                    Cell cxcascreenedcell = dataRow.getCell(36);

                    if (cxcascreenedcell == null) {
                        cxcascreened = 0.0;
                    } else {
                        cxcascreened = dataRow.getCell(36).getNumericCellValue();
                    }

                    Cell cxcascreenedposcell = dataRow.getCell(37);

                    if (cxcascreenedposcell == null) {
                        cxcascreenedpos = 0.0;
                    } else {
                        cxcascreenedpos = dataRow.getCell(37).getNumericCellValue();
                    }

                    Cell cxcareceivedtreatmentcell = dataRow.getCell(38);

                    if (cxcareceivedtreatmentcell == null) {
                        cxcareceivedtreatment = 0.0;
                    } else {
                        cxcareceivedtreatment = dataRow.getCell(38).getNumericCellValue();
                    }

                    Cell cxcasuspectedcell = dataRow.getCell(39);

                    if (cxcasuspectedcell == null) {
                        cxcasuspected = 0.0;
                    } else {
                        cxcasuspected = dataRow.getCell(39).getNumericCellValue();
                    }

                    Cell currneverontptcell = dataRow.getCell(40);

                    if (currneverontptcell == null) {
                        currneverontpt = 0.0;
                    } else {
                        currneverontpt = dataRow.getCell(40).getNumericCellValue();
                    }

                    Cell screenednegfortbcell = dataRow.getCell(41);

                    if (screenednegfortbcell == null) {
                        screenednegfortb = 0.0;
                    } else {
                        screenednegfortb = dataRow.getCell(41).getNumericCellValue();
                    }

                    Cell startedtpttxnewcell = dataRow.getCell(42);

                    if (startedtpttxnewcell == null) {
                        startedtpttxnew = 0.0;
                    } else {
                        startedtpttxnew = dataRow.getCell(42).getNumericCellValue();
                    }

                    Cell startedtpttxcurrcell = dataRow.getCell(43);

                    if (startedtpttxcurrcell == null) {
                        startedtpttxcurr = 0.0;
                    } else {
                        startedtpttxcurr = dataRow.getCell(43).getNumericCellValue();
                    }

                    Cell startedtptcell = dataRow.getCell(44);

                    if (startedtptcell == null) {
                        startedtpt = 0.0;
                    } else {
                        startedtpt = dataRow.getCell(44).getNumericCellValue();
                    }

                    Cell daily1st95childrentestedcell = dataRow.getCell(45);

                    if (daily1st95childrentestedcell == null) {
                        daily1st95childrentested = 0.0;
                    } else {
                        daily1st95childrentested = dataRow.getCell(45).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95cacxtargetedwracell = dataRow.getCell(46);

                    if (daily2ndand3rd95cacxtargetedwracell == null) {
                        daily2ndand3rd95cacxtargetedwra = 0.0;
                    } else {
                        daily2ndand3rd95cacxtargetedwra = dataRow.getCell(46).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95eligibleforvltestcell = dataRow.getCell(47);

                    if (daily2ndand3rd95eligibleforvltestcell == null) {
                        daily2ndand3rd95eligibleforvltest = 0.0;
                    } else {
                        daily2ndand3rd95eligibleforvltest = dataRow.getCell(47).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95hpv10to14yrseligiblecell = dataRow.getCell(48);

                    if (daily2ndand3rd95hpv10to14yrseligiblecell == null) {
                        daily2ndand3rd95hpv10to14yrseligible = 0.0;
                    } else {
                        daily2ndand3rd95hpv10to14yrseligible = dataRow.getCell(48).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95hpv10to14yrs95vaccinatedcell = dataRow.getCell(49);

                    if (daily2ndand3rd95hpv10to14yrs95vaccinatedcell == null) {
                        daily2ndand3rd95hpv10to14yrs95vaccinated = 0.0;
                    } else {
                        daily2ndand3rd95hpv10to14yrs95vaccinated = dataRow.getCell(49).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95newlyiitcell = dataRow.getCell(50);

                    if (daily2ndand3rd95newlyiitcell == null) {
                        daily2ndand3rd95newlyiit = 0.0;
                    } else {
                        daily2ndand3rd95newlyiit = dataRow.getCell(50).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbcccscreenedcell = dataRow.getCell(51);

                    if (daily2ndand3rd95tbcccscreenedcell == null) {
                        daily2ndand3rd95tbcccscreened = 0.0;
                    } else {
                        daily2ndand3rd95tbcccscreened = dataRow.getCell(51).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbcccworkloadcell = dataRow.getCell(52);

                    if (daily2ndand3rd95tbcccworkloadcell == null) {
                        daily2ndand3rd95tbcccworkload = 0.0;
                    } else {
                        daily2ndand3rd95tbcccworkload = dataRow.getCell(52).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbopdscreenedcell = dataRow.getCell(53);

                    if (daily2ndand3rd95tbopdscreenedcell == null) {
                        daily2ndand3rd95tbopdscreened = 0.0;
                    } else {
                        daily2ndand3rd95tbopdscreened = dataRow.getCell(53).getNumericCellValue();
                    }

                    Cell daily2ndand3rd95tbopdworkloadcell = dataRow.getCell(54);

                    if (daily2ndand3rd95tbopdworkloadcell == null) {
                        daily2ndand3rd95tbopdworkload = 0.0;
                    } else {
                        daily2ndand3rd95tbopdworkload = dataRow.getCell(54).getNumericCellValue();
                    }

                    Cell otherhivtestscell = dataRow.getCell(55);

                    if (otherhivtestscell == null) {
                        otherhivtests = 0.0;
                    } else {
                        otherhivtests = dataRow.getCell(55).getNumericCellValue();
                    }

                    Cell knownstatuskpplustestedcell = dataRow.getCell(56);

                    if (knownstatuskpplustestedcell == null) {
                        knownstatuskpplustested = 0.0;
                    } else {
                        knownstatuskpplustested = dataRow.getCell(56).getNumericCellValue();
                    }

                    Cell pmtctstattdcell = dataRow.getCell(57);

                    if (pmtctstattdcell == null) {
                        pmtctstattd = 0.0;
                    } else {
                        pmtctstattd = dataRow.getCell(57).getNumericCellValue();
                    }

                    Cell tptcummulativecell = dataRow.getCell(58);

                    if (tptcummulativecell == null) {
                        tptcummulative = 0.0;
                    } else {
                        tptcummulative = dataRow.getCell(58).getNumericCellValue();
                    }

                    Cell completedtptcell = dataRow.getCell(59);

                    if (completedtptcell == null) {
                        completedtpt = 0.0;
                    } else {
                        completedtpt = dataRow.getCell(59).getNumericCellValue();
                    }

                    Cell completedtpttxnewcell = dataRow.getCell(60);

                    if (completedtpttxnewcell == null) {
                        completedtpttxnew = 0.0;
                    } else {
                        completedtpttxnew = dataRow.getCell(60).getNumericCellValue();
                    }

                    Cell completedtpttxcurrcell = dataRow.getCell(61);

                    if (completedtpttxcurrcell == null) {
                        completedtpttxcurr = 0.0;
                    } else {
                        completedtpttxcurr = dataRow.getCell(61).getNumericCellValue();
                    }

                    Cell missedhivtestingtargetcell = dataRow.getCell(62);

                    if (missedhivtestingtargetcell == null) {
                        missedhivtestingtarget = 0.0;
                    } else {
                        missedhivtestingtarget = dataRow.getCell(62).getNumericCellValue();
                    }

                    Cell reachedandaccountedcell = dataRow.getCell(63);

                    if (reachedandaccountedcell == null) {
                        reachedandaccounted = 0.0;
                    } else {
                        reachedandaccounted = dataRow.getCell(63).getNumericCellValue();
                    }

                    Cell newiitclientscell = dataRow.getCell(64);

                    if (newiitclientscell == null) {
                        newiitclients = 0.0;
                    } else {
                        newiitclients = dataRow.getCell(64).getNumericCellValue();
                    }

                    Cell tptuptakeeligiblefortptcell = dataRow.getCell(65);

                    if (tptuptakeeligiblefortptcell == null) {
                        tptuptakeeligiblefortpt = 0.0;
                    } else {
                        tptuptakeeligiblefortpt = dataRow.getCell(65).getNumericCellValue();
                    }

                    Cell tptuptakestartedontptcell = dataRow.getCell(66);

                    if (tptuptakestartedontptcell == null) {
                        tptuptakestartedontpt = 0.0;
                    } else {
                        tptuptakestartedontpt = dataRow.getCell(66).getNumericCellValue();
                    }

                    Cell prepnewagywcell = dataRow.getCell(67);

                    if (prepnewagywcell == null) {
                        prepnewagyw = 0.0;
                    } else {
                        prepnewagyw = dataRow.getCell(67).getNumericCellValue();
                    }

                    Cell weekcell = dataRow.getCell(68);

                    if (weekcell == null) {
                        week = 0.0;
                    } else {
                        week = dataRow.getCell(68).getNumericCellValue();
                    }

                    Cell monthcell = dataRow.getCell(69);

                    if (monthcell == null) {
                        month = 0.0;
                    } else {
                        month = dataRow.getCell(69).getNumericCellValue();
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
                    rri.setCreated_by(userdetails.getId());
                    rri.setCreated_on(nowDate);

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
