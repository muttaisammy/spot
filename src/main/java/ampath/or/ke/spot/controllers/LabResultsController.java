package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Programs;
import ampath.or.ke.spot.services.LabResultsService;
import ampath.or.ke.spot.services.ProgramsService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.util.Date;

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

        for (int x =1;x<totalRowNum;x++){
            String ccc="",pname="",county="",scounty="",fname="",mfl="",order="",location="",recno="";
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
//================================
                if (sheet.getRow(x).getCell(8) == null) {
                    recno = "";
                }else {
                    if (sheet.getRow(x).getCell(8).getCellType().equals(CellType.NUMERIC)) {
                        recno = String.valueOf(sheet.getRow(x).getCell(8).getNumericCellValue());
                    } else {
                        recno = String.valueOf(sheet.getRow(x).getCell(8).getStringCellValue());
                    }
                }

                // ===============================


            }
            System.out.println("CCC number " +ccc +" " +pname +" "+ location +" "+recno +" "+order);
        }


        System.out.println("Rows ndo Hii "+ totalRowNum);

        //uploadlab(destFileName,destFile,dataset);
        return "Submited";
    }
}
