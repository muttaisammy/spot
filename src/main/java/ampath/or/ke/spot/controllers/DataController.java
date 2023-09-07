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
            
            InputStream fis = new FileInputStream(data_path+"facilities.xlsx");
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
                for(int x=1;x<=totalRowNum;x++){
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
                    
                  
                   String mflcode= nmflcode.substring(0, nmflcode.length() - 2);
                    System.out.println("Row" + x + " Facility "+ facility + " mfclode "+ nmflcode+ " nmflcode "+mflcode);
                   Facilities  facilities= facilitiesService.getByMFLCODE("nmflcode");
                   if(facilities==null){
                    Facilities f = new Facilities();
                     UUID uuid = UUID.randomUUID();
                        f.setUuid( String.valueOf(uuid));
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
            
            InputStream fis = new FileInputStream(data_path+"rri_data.xlsx");
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
               
                for(int x=1;x<=totalRowNum;x++){
                    
                    Row dataRow = sheet.getRow(x);
                    String nmflcode = String.valueOf(dataRow.getCell(0).getNumericCellValue());
                    String datatype = String.valueOf(dataRow.getCell(1).getStringCellValue());
                    double tst = 0.0;
                    double pos = 0.0;
                    double hstlink = 0.0;

                    Cell tstcell = dataRow.getCell(2);

                    if (tstcell == null) {
                        tst = 0.0;
                    }else{
                      tst =  dataRow.getCell(2).getNumericCellValue();
                    }
                    Cell poscell = dataRow.getCell(3);

                    if (poscell == null) {
                        pos = 0.0;
                    }else{
                      pos =  dataRow.getCell(3).getNumericCellValue();
                       // pos = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0, String.valueOf(dataRow.getCell(3).getNumericCellValue()).length() - 2);
                      
                    }
                    Cell hstlinkcell = dataRow.getCell(4);

                    if (hstlinkcell == null) {
                        hstlink = 0.0;
                    }else{
                      hstlink =  dataRow.getCell(4).getNumericCellValue();
                    }

                   String mflcode= nmflcode.substring(0, nmflcode.length() - 2);
                   
                   System.out.println("Row" + x + " mfclode "+ nmflcode+ " nmflcode "+mflcode+" TST "+tst +" POS "+ pos +" LINK "+hstlink);
                    UUID uuid = UUID.randomUUID();
                     RRI rri = new RRI();
                        rri.setCategory(datatype);
                        rri.setUuid(String.valueOf(uuid));
                        rri.setHst_pos((int) pos);
                        rri.setHst_tst((int) tst);
                        rri.setHst_link((int) hstlink);
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
