package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.LabResults;
import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.LabResultsService;
import ampath.or.ke.spot.services.ProgramsService;
import org.apache.poi.hpsf.Decimal;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/labs")
public class LabResultsController {
    @Value("${app.dir}")
    public String data_path;
    @Autowired
    public LabResultsService labResultsService;

    @RequestMapping(value = "/upload_CSV", method = RequestMethod.GET)
    public ModelAndView overview(HttpSession session) throws IOException {
        if (session.getAttribute("company") != null && session.getAttribute("user") != null) {
            Programs companydetails = (Programs) session.getAttribute("companydetails");
            ModelAndView modelAndView = new ModelAndView();
            Date nowDate = new Date();
            modelAndView.addObject("today", nowDate);
            modelAndView.setViewName("lab_results");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/setup");
        }
    }

    @RequestMapping(value = "/process/csv", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file
    ) throws IOException, InvalidFormatException, JSONException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String progres = "";
        if (file.isEmpty()) {
            progres = "Upload failed, please select file";
        }
        String fileName = file.getOriginalFilename();
        String dir = data_path ;// System.getProperty("user.dir");
        Date dates = new Date();
        String destFileName = dir +  File.separator + "uploadedfiles_" + fileName;
        System.out.println(destFileName);
        File destFile = new File(destFileName);
        boolean exists = destFile.exists();
        System.out.println("File exists " + exists);
        if (exists == true) {
            destFile.deleteOnExit();//delete();
        } else {
            file.transferTo(destFile);
        }
        System.out.println("Upload succeeded");
        System.out.println("Start reading EXCEL content");
        Sheet sheet;
        InputStream fis = null;
        fis = new FileInputStream(destFileName);
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(destFile);
        } catch (Exception ex) {
            workbook = new HSSFWorkbook(fis);
        }
        int nsheets = workbook.getNumberOfSheets();
        sheet = workbook.getSheetAt(0);
        int totalRowNum = sheet.getLastRowNum();
        Row facilityRow = sheet.getRow(2);

        for (int x =1;x<=totalRowNum;x++){
            String ccc="",pname="",county="",scounty="",fname="",mfl="",order="",location="",recno="",sex="",dob="",age="",pmtct="",stype="",collectdate="",receivedstatus="",currregimen="",artinitdate="",justification="",datereceived="",dateentered="",datetest="",dateapp="",datedisp="",viralload="",entry="";
           Date dob_Date = null,COllect_Date = null,Received_Date = null,Entry_Date = null,Test_Date= null,Approval_Date= null,Disp_Date= null,Initiation_Date= null;
            System.out.println("Cell Type "+sheet.getRow(x).getCell(0).getCellType() + " Row Number "+ x);

            if(sheet.getRow(x).getCell(0)==null){

            }
            else {
                if (sheet.getRow(x).getCell(0).getCellType().equals(CellType.NUMERIC)) {
                    ccc = String.valueOf(sheet.getRow(x).getCell(0).getNumericCellValue());
                } else {
                    ccc = String.valueOf(sheet.getRow(x).getCell(0).getStringCellValue());
                }
                if (sheet.getRow(x).getCell(1) == null) {
                    pname = "";
                }else {
                    if (sheet.getRow(x).getCell(1).getCellType().equals(CellType.NUMERIC)) {
                        pname = String.valueOf(sheet.getRow(x).getCell(1).getNumericCellValue());
                    } else {
                        pname = String.valueOf(sheet.getRow(x).getCell(1).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(2) == null) {
                    county = "";
                }else {
                    if (sheet.getRow(x).getCell(2).getCellType().equals(CellType.NUMERIC)) {
                        county = String.valueOf(sheet.getRow(x).getCell(2).getNumericCellValue());
                    } else {
                        county = String.valueOf(sheet.getRow(x).getCell(2).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(3) == null) {
                    scounty = "";
                }else {
                    if (sheet.getRow(x).getCell(3).getCellType().equals(CellType.NUMERIC)) {
                        scounty = String.valueOf(sheet.getRow(x).getCell(3).getNumericCellValue());
                    } else {
                        scounty = String.valueOf(sheet.getRow(x).getCell(3).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(4) == null) {
                    fname = "";
                }else {
                    if (sheet.getRow(x).getCell(4).getCellType().equals(CellType.NUMERIC)) {
                        fname = String.valueOf(sheet.getRow(x).getCell(4).getNumericCellValue());
                    } else {
                        fname = String.valueOf(sheet.getRow(x).getCell(4).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(5) == null) {
                    mfl = "";
                }else {
                    if (sheet.getRow(x).getCell(5).getCellType().equals(CellType.NUMERIC)) {
                        mfl = String.valueOf(sheet.getRow(x).getCell(5).getNumericCellValue());
                    } else {
                        mfl = String.valueOf(sheet.getRow(x).getCell(5).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(6) == null) {
                    order = "";
                } else {
                    if (sheet.getRow(x).getCell(6).getCellType().equals(CellType.NUMERIC)) {
                        order = String.valueOf(sheet.getRow(x).getCell(6).getNumericCellValue());
                    } else {
                        order = String.valueOf(sheet.getRow(x).getCell(6).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(7) == null) {
                    location = "";
                }else {
                    if (sheet.getRow(x).getCell(7).getCellType().equals(CellType.NUMERIC)) {
                        location = String.valueOf(sheet.getRow(x).getCell(7).getNumericCellValue());
                    } else {
                        location = String.valueOf(sheet.getRow(x).getCell(7).getStringCellValue());
                    }
                }
                if (sheet.getRow(x).getCell(8) == null) {
                    recno = "";
                }else {
                    if (sheet.getRow(x).getCell(8).getCellType().equals(CellType.NUMERIC)) {
                        recno = String.valueOf(sheet.getRow(x).getCell(8).getNumericCellValue());
                    } else {
                        recno = String.valueOf(sheet.getRow(x).getCell(8).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(9) == null) {
                    sex = "";
                }else {
                    if (sheet.getRow(x).getCell(9).getCellType().equals(CellType.NUMERIC)) {
                        sex = String.valueOf(sheet.getRow(x).getCell(9).getNumericCellValue());
                    } else {
                        sex = String.valueOf(sheet.getRow(x).getCell(9).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(10) == null) {
                    dob = "";
                }else {
                    if (sheet.getRow(x).getCell(10).getCellType().equals(CellType.NUMERIC)) {
                        dob = String.valueOf(sheet.getRow(x).getCell(10).getNumericCellValue());
                        dob_Date = DateUtil.getJavaDate((double) Float.parseFloat(dob));
                    } else {
                        dob = String.valueOf(sheet.getRow(x).getCell(10).getStringCellValue());
                        dob_Date = DateUtil.getJavaDate((double) Float.parseFloat(dob));
                    }
                }

                if (sheet.getRow(x).getCell(11) == null) {
                    age = "";
                }else {
                    if (sheet.getRow(x).getCell(11).getCellType().equals(CellType.NUMERIC)) {
                        age = String.valueOf(sheet.getRow(x).getCell(11).getNumericCellValue());
                    } else {
                        age = String.valueOf(sheet.getRow(x).getCell(11).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(12) == null) {
                    pmtct = "";
                }else {
                    if (sheet.getRow(x).getCell(12).getCellType().equals(CellType.NUMERIC)) {
                        pmtct = String.valueOf(sheet.getRow(x).getCell(12).getNumericCellValue());
                    } else {
                        pmtct = String.valueOf(sheet.getRow(x).getCell(12).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(13) == null) {
                    stype = "";
                }else {
                    if (sheet.getRow(x).getCell(13).getCellType().equals(CellType.NUMERIC)) {
                        stype = String.valueOf(sheet.getRow(x).getCell(13).getNumericCellValue());
                    } else {
                        stype = String.valueOf(sheet.getRow(x).getCell(13).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(14) == null) {
                    collectdate = "";
                }else {
                    if (sheet.getRow(x).getCell(14).getCellType().equals(CellType.NUMERIC)) {
                        collectdate = String.valueOf(sheet.getRow(x).getCell(14).getNumericCellValue());
                        COllect_Date = DateUtil.getJavaDate((double) Float.parseFloat(collectdate));
                    } else {
                        collectdate = String.valueOf(sheet.getRow(x).getCell(14).getStringCellValue());
                        COllect_Date = DateUtil.getJavaDate((double) Float.parseFloat(collectdate));
                    }
                }

                if (sheet.getRow(x).getCell(15) == null) {
                    receivedstatus = "";
                }else {
                    if (sheet.getRow(x).getCell(15).getCellType().equals(CellType.NUMERIC)) {
                        receivedstatus = String.valueOf(sheet.getRow(x).getCell(15).getNumericCellValue());
                    } else {
                        receivedstatus = String.valueOf(sheet.getRow(x).getCell(15).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(16) == null) {
                    currregimen = "";
                }else {
                    if (sheet.getRow(x).getCell(16).getCellType().equals(CellType.NUMERIC)) {
                        currregimen = String.valueOf(sheet.getRow(x).getCell(16).getNumericCellValue());
                    } else {
                        currregimen = String.valueOf(sheet.getRow(x).getCell(16).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(17) == null) {
                    artinitdate = "";
                }else {
                    if (sheet.getRow(x).getCell(17).getCellType().equals(CellType.NUMERIC)) {
                        artinitdate = String.valueOf(sheet.getRow(x).getCell(17).getNumericCellValue());
                        Initiation_Date = DateUtil.getJavaDate((double) Float.parseFloat(artinitdate));
                    } else {
                        artinitdate = String.valueOf(sheet.getRow(x).getCell(17).getStringCellValue());
                        Initiation_Date =DateUtil.getJavaDate((double) Float.parseFloat(artinitdate));
                    }
                }

                if (sheet.getRow(x).getCell(18) == null) {
                    justification = "";
                }else {
                    if (sheet.getRow(x).getCell(18).getCellType().equals(CellType.NUMERIC)) {
                        justification = String.valueOf(sheet.getRow(x).getCell(18).getNumericCellValue());
                    } else {
                        justification = String.valueOf(sheet.getRow(x).getCell(18).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(19) == null) {
                    datereceived = "";
                }else {
                    if (sheet.getRow(x).getCell(19).getCellType().equals(CellType.NUMERIC)) {
                        datereceived = String.valueOf(sheet.getRow(x).getCell(19).getNumericCellValue());
                        Received_Date = DateUtil.getJavaDate((double) Float.parseFloat(datereceived));
                    } else {
                        datereceived = String.valueOf(sheet.getRow(x).getCell(19).getStringCellValue());
                        Received_Date = DateUtil.getJavaDate((double) Float.parseFloat(datereceived));
                    }
                }

                if (sheet.getRow(x).getCell(20) == null) {
                    dateentered = "";
                }else {
                    if (sheet.getRow(x).getCell(20).getCellType().equals(CellType.NUMERIC)) {
                        dateentered = String.valueOf(sheet.getRow(x).getCell(20).getNumericCellValue());
                        Entry_Date = DateUtil.getJavaDate((double) Float.parseFloat(dateentered));
                    } else {
                        dateentered = String.valueOf(sheet.getRow(x).getCell(20).getStringCellValue());
                        Entry_Date = DateUtil.getJavaDate((double) Float.parseFloat(dateentered));
                    }
                }

                if (sheet.getRow(x).getCell(21) == null) {
                    datetest = "";
                }else {
                    if (sheet.getRow(x).getCell(21).getCellType().equals(CellType.NUMERIC)) {
                        datetest = String.valueOf(sheet.getRow(x).getCell(21).getNumericCellValue());
                        Test_Date = DateUtil.getJavaDate((double) Float.parseFloat(datetest));
                    } else {
                        datetest = String.valueOf(sheet.getRow(x).getCell(21).getStringCellValue());
                        Test_Date = DateUtil.getJavaDate((double) Float.parseFloat(datetest));
                    }
                }

                if (sheet.getRow(x).getCell(22) == null) {
                    dateapp = "";
                }else {
                    if (sheet.getRow(x).getCell(22).getCellType().equals(CellType.NUMERIC)) {
                        dateapp = String.valueOf(sheet.getRow(x).getCell(22).getNumericCellValue());
                        Approval_Date = DateUtil.getJavaDate((double) Float.parseFloat(dateapp));
                    } else {
                        dateapp = String.valueOf(sheet.getRow(x).getCell(22).getStringCellValue());
                        Approval_Date = DateUtil.getJavaDate((double) Float.parseFloat(dateapp));
                    }
                }

                if (sheet.getRow(x).getCell(23) == null) {
                    datedisp = "";
                }else {
                    if (sheet.getRow(x).getCell(23).getCellType().equals(CellType.NUMERIC)) {
                        datedisp = String.valueOf(sheet.getRow(x).getCell(23).getNumericCellValue());
                         Disp_Date = DateUtil.getJavaDate((double) Float.parseFloat(datedisp));
                    } else {
                        datedisp = String.valueOf(sheet.getRow(x).getCell(23).getStringCellValue());
                        Disp_Date = DateUtil.getJavaDate((double) Float.parseFloat(datedisp));
                    }
                }

                if (sheet.getRow(x).getCell(24) == null) {
                    viralload = "";
                }else {
                    if (sheet.getRow(x).getCell(24).getCellType().equals(CellType.NUMERIC)) {
                        viralload = String.valueOf(sheet.getRow(x).getCell(24).getNumericCellValue());
                    } else {
                        viralload = String.valueOf(sheet.getRow(x).getCell(24).getStringCellValue());
                    }
                }

                if (sheet.getRow(x).getCell(25) == null) {
                    entry = "";
                }else {
                    if (sheet.getRow(x).getCell(25).getCellType().equals(CellType.NUMERIC)) {
                        entry = String.valueOf(sheet.getRow(x).getCell(25).getNumericCellValue());
                    } else {
                        entry = String.valueOf(sheet.getRow(x).getCell(25).getStringCellValue());
                    }
                }


            }
             System.out.println("CCC number " +ccc +" " +pname +" "+county +" "+scounty +" "+fname +" "+mfl +" "+order +" "+ location +" "+recno +" "+sex +" "+dob +" "+age + " "+pmtct +" "+stype + " "+collectdate +" " +receivedstatus +" "+currregimen +" ART_initiation  "+artinitdate +" Justification "+justification +" Date Recevced "+datereceived +" Date entred "+dateentered +" Date Tested "+datetest +" Date Approved"+dateapp +" Date Dispende "+datedisp +" javaDate "+ dob_Date+' '+COllect_Date+' '+Received_Date+' '+Entry_Date+' '+Test_Date+' '+Approval_Date+' '+Disp_Date+ " "+viralload +" "+entry );
            List<LabResults> labResults = labResultsService.findByCCCAndCollectionDate(ccc,COllect_Date);
             int labsize = labResults.size();
             System.out.println("Lab Size is "+ labsize);
             if(labsize==0){
                 LabResults results = new LabResults();
                 results.setAge(age);
                 results.setCcc(ccc);
                 results.setCounty(county);
                 results.setAmrsLocation(location);
                 results.setDob(dob_Date);
                 results.setDateEntered(Entry_Date);
                 results.setArtInitiationDate(Initiation_Date);
                 results.setCurrRegimen(currregimen);
                 results.setDateOfDispach(Disp_Date);
                 results.setDateOfApproval(Approval_Date);
                 results.setMflcode(mfl);
                 results.setFname(fname);
                 results.setSex(sex);
                 results.setSampleType(stype);
                 results.setJustification(justification);
                 results.setPmtct(pmtct);
                 results.setViralLoad(viralload);
                 results.setReceivedStatus(receivedstatus);
                 results.setEntry(entry);
                 results.setDateReceived(Received_Date);
                 results.setDateOfApproval(Approval_Date);
                 results.setCollectionDate(COllect_Date);
                 results.setCounty(county);
                 results.setSubCounty(scounty);
                 results.setDateOfTesting(Test_Date);
                 results.setPname(pname);
                 labResultsService.save(results);

                 System.out.println("Lab Size is "+ labsize+" ");
             }else{
                 LabResults results = labResults.get(0);
                 results.setAge(age);
                 results.setCcc(ccc);
                 results.setCounty(county);
                 results.setAmrsLocation(location);
                 results.setDob(dob_Date);
                 results.setDateEntered(Entry_Date);
                 results.setArtInitiationDate(Initiation_Date);
                 results.setCurrRegimen(currregimen);
                 results.setDateOfDispach(Disp_Date);
                 results.setDateOfApproval(Approval_Date);
                 results.setMflcode(mfl);
                 results.setFname(fname);
                 results.setSex(sex);
                 results.setSampleType(stype);
                 results.setJustification(justification);
                 results.setPmtct(pmtct);
                 results.setViralLoad(viralload);
                 results.setReceivedStatus(receivedstatus);
                 results.setEntry(entry);
                 results.setDateReceived(Received_Date);
                 results.setDateOfApproval(Approval_Date);
                 results.setCollectionDate(COllect_Date);
                 results.setCounty(county);
                 results.setSubCounty(scounty);
                 results.setDateOfTesting(Test_Date);
                 results.setPname(pname);
                 labResultsService.save(results);
                 System.out.println("Lab Size is "+ labsize+" textt ");
             }



        }


        System.out.println("Rows ndo Hii "+ totalRowNum);

        //uploadlab(destFileName,destFile,dataset);
        return "Submited";
    }
}
