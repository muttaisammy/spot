package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.DatabasesInfo;
import ampath.or.ke.spot.models.Facilities;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.DatabasesInfoServices;
import ampath.or.ke.spot.services.FacilitiesService;
import ampath.or.ke.spot.services.MonthsService;
import ampath.or.ke.spot.services.YearsService;
import ampath.or.ke.spot.utils.Helper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;


@Controller
@Transactional
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    DatabasesInfoServices databasesInfoServices;
    @Autowired
    public FacilitiesService facilitiesService;
    @Autowired
    public YearsService yearsService;
    @Autowired
    public MonthsService monthsService;
    @Value("${app.dbpath}")
    public String dbpath;
    @Value("${spring.datasource.username}")
    public String username;
    @Value("${spring.datasource.password}")
    public String password;
    @Value("${spring.datasource.jdbcUrl}")
    public String dburl;
    @Value("${app.mysqlpath}")
    public String mysqlpath;

    @RequestMapping(value = "/master", method = RequestMethod.GET)
    public ModelAndView masterdatabase(HttpSession session) throws IOException, JSONException {
        System.out.println("Imefika Hapa");
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            List<DatabasesInfo> databasesInfos = databasesInfoServices.getAllDataset();

          /*  List<Facilities> facilitiesList = facilitiesService.searchByFtypeLike("F");
            List<Facilities> contiesList = facilitiesService.searchByFtypeLike("C");
            List<DatabaseInfo> databaseInfos = databaseinfoService.getAllDataset();*/

          //  modelAndView.addObject("dbs", databaseInfos);
         //   modelAndView.addObject("facilities", facilitiesService.searchByFtypeLike("F"));
         //   modelAndView.addObject("counties", facilitiesService.searchByFtypeLike("C"));
         //   modelAndView.addObject("countfacilities", facilitiesList.size());
            modelAndView.addObject("smg","0");// new Helper("success", "Successfully save project."));
            modelAndView.addObject("years", yearsService.getAllDataset());
            modelAndView.addObject("months", monthsService.getAllDataset());
            modelAndView.addObject("facility", facilitiesService.KenyaEMRFacilities("KenyaEMR"));
            modelAndView.addObject("dbs", databasesInfos);
            modelAndView.setViewName("databases");
            //modelAndView.addAttribute("message", new helper("success", "Successfully save project."));

            return modelAndView;
        } else {
            return new ModelAndView("redirect:/auth/login");
        }

    }
    @RequestMapping(value = "/master", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView upload(@RequestParam("filename") MultipartFile file,
                         @RequestParam("facility") String facility,
                         @RequestParam("year") String year,
                         @RequestParam("month") String month,
                         HttpSession session
    ) throws IOException, InvalidFormatException, JSONException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView();
            List<DatabasesInfo> databasesInfos = databasesInfoServices.getAllDataset();
        String progres = "";
        User userdetails = (User) session.getAttribute("user");
        //String fileName = file.getOriginalFilename();
        //File newfile = new File(dbpath+"Rugutetest");
        //file.transferTo(newfile);
        long size = file.getSize();
        System.out.println("File size "+ size);

        String nfilename = "openmrsm" + facility + month + year + ".sql";
        String fpath = dbpath + nfilename;
        Date nowDate = new Date();
       // try {

            //Unzipping the file
            String fileName = file.getOriginalFilename();

            System.out.println("Full name is "+ fileName);
           // Path dest = Paths.get(dbpath + nfilename);
           // Path source = Paths.get(Arrays.toString(file.getBytes()));
            int index = fileName.lastIndexOf('.');
            String extension="";
            if(index > 0) {
                extension = fileName.substring(index + 1);
                System.out.println("File extension is " + extension);
            }
            // if(extension.equals(".sql")){
                file.transferTo(new File(dbpath + nfilename));
                long bytes = file.getSize();
                long gbc = (1024*3);
                long gb = bytes/gbc;

                long kilobytes = (bytes / 1024);
                long megabytes = (kilobytes / 1024);
                long gigabytes = (megabytes / 1024);
          /*  }else{
                String storeLocation = file.getResource().getFile().getAbsolutePath();
                System.out.println("File location "+storeLocation);
                unGunzipFile(storeLocation,dbpath + nfilename);
            } */

            //Path path = Paths.get(nfilename);

            //long bytes = Files.size(path);
            UUID uuid = UUID.randomUUID();

            DatabasesInfo databaseInfo = new DatabasesInfo();
            Facilities facilities = facilitiesService.getByMFLCODE((facility));
            databaseInfo.setDbname(facilities.getFacilityname());
            databaseInfo.setMflcode(facility);
            databaseInfo.setUuid(uuid.toString());
            databaseInfo.setReuploaded("No");
            databaseInfo.setCreated_by(userdetails.getId());
            databaseInfo.setUrl(fpath);
            databaseInfo.setStatus("Pending restore");
            databaseInfo.setFacilityname(facilities.getFacilityname());
            // databaseInfo.setDbsize(String.format("%.2f,d GB", gigabytes));
            databaseInfo.setDbsize(String.valueOf(megabytes+" MB"));
            databaseInfo.setDbname(nfilename);
            databaseInfo.setCreated_on(nowDate);
            //databaseInfo.set;
            databasesInfoServices.save(databaseInfo);

       // } catch (Exception e) {
       //     return HttpStatus.INTERNAL_SERVER_ERROR.toString();
       // }
        //modelMap.addAttribute("filename", file);
        System.out.println("Upload imefika hapa" + userdetails.getFull_name() + " " + facility + " " + year + " " + month);
        //return "File uploaded successfully.";

        modelAndView.addObject("smg", "1");
        modelAndView.addObject("years", yearsService.getAllDataset());
        modelAndView.addObject("months", monthsService.getAllDataset());
        modelAndView.addObject("facility", facilitiesService.KenyaEMRFacilities("KenyaEMR"));
        modelAndView.addObject("dbs", databasesInfos);
        modelAndView.addObject("smg", new Helper("success", "Successfully save project."));
        modelAndView.setViewName("databases");
        // modelAndView.addAttribute("message", new helper("success", "Successfully save project."));

        return modelAndView;
    } else {
        return new ModelAndView("redirect:/auth/login");
    }

    }

    public void unGunzipFile(String compressedFile, String decompressedFile) {

        byte[] buffer = new byte[1024];

        try {

            FileInputStream fileIn = new FileInputStream(compressedFile);

            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);

            FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);

            int bytes_read;

            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer, 0, bytes_read);
            }

            gZIPInputStream.close();
            fileOutputStream.close();

            System.out.println("The file was decompressed successfully!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/restore/{fileName:.+}")
    @ResponseBody
    public String restoreFileFromLocal(@PathVariable String fileName) throws IOException, InterruptedException {
        String fpath = dbpath;
        String smg = "";
        String realPathtoUploads = dbpath + fileName;
        String nfilename = fileName.substring(0, fileName.length() - 4);

        try {

            Connection connection = DriverManager.getConnection(dburl, username, password);
            System.out.println("New file name is " + nfilename+" "+ realPathtoUploads);
            String sql = "CREATE DATABASE " + nfilename;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            String dbname = nfilename+".sql";


            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabasesInfo databaseInfo = databasesInfoServices.getByDbname(dbname);
                    databaseInfo.setStatus("Restoring");
                    databasesInfoServices.save(databaseInfo);

                   // restorebd(nfilename, realPathtoUploads);
                    try {

                        String[] restoreCmd = new String[]{"mysql", "-u'"+ username +"'", "-p'"+ password +"'", nfilename , "-e", "source " + realPathtoUploads};
                        Runtime rt =Runtime.getRuntime();
                        rt.exec(restoreCmd);
                        System.out.println("Restored successfully!");

                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    DatabasesInfo databaseInfo1 = databasesInfoServices.getByDbname(dbname);
                    databaseInfo1.setStatus("Restored");
                    databasesInfoServices.save(databaseInfo1);
                }
            }).start();
            smg = "Database created successfully";
        } catch (SQLException e) {
            String dbname = nfilename+".sql";
            System.out.println("New file name is " + nfilename+" "+ realPathtoUploads);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabasesInfo databaseInfo = databasesInfoServices.getByDbname(dbname);
                    databaseInfo.setStatus("Restoring");
                    databasesInfoServices.save(databaseInfo);

                  //  restorebd(nfilename, realPathtoUploads);
                    try {

                        String[] restoreCmd = new String[]{"mysql", "-u'"+ username +"'", "-p'"+ password +"'", nfilename , "-e", "source " + realPathtoUploads};
                        Runtime rt =Runtime.getRuntime();
                        rt.exec(restoreCmd);
                        System.out.println("Restored successfully!");

                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    DatabasesInfo databaseInfo1 = databasesInfoServices.getByDbname(dbname);
                    databaseInfo1.setStatus("Restored");
                    databaseInfo1.setReuploaded("Yes");
                    databasesInfoServices.save(databaseInfo1);
                }
            }).start();

            smg = e.getMessage()+ "Mysql database already exist";


        }
        return smg;
    }
    public String restorebd(String nfilename, String realPathtoUploads ){
        String smg = "";
        //String command ="\"mysql\", \"--user=" + username+", \"--password=\" + password, nfilename, \"-e\", \" source \"" + realPathtoUploads;

        String[] executeCmd = new String[]{"mysql", "--user=" + username, " --password=" + password+", "+ nfilename, " -e", " source " + realPathtoUploads};

     //   String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, nfilename, "-e", " source " + realPathtoUploads};
       // String[] executeCmd = new String[]{mysqlpath, "--user=" + username, "--password=" + password, nfilename,"-e", " source " + realPathtoUploads};
        System.out.println(" Restore Db command "+executeCmd.toString()+" Command" );

        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                smg = "Backup restored successfully";//return true;
            } else {
                smg = "Could not restore the backup";

            }
            return smg;//"Restored Successfully";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return smg;//"Restored Successfully";
    }
    @GetMapping("/drop/{fileName}")
    // @ResponseBody
    public ModelAndView dropdatabase(@PathVariable String fileName) throws IOException, InterruptedException {
        String fpath = dbpath;
        final String smg = "";
        // String realPathtoUploads = dbpath + fileName;
        String nfilename = fileName.substring(0, fileName.length() - 4);
        System.out.println("Droping databases");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection connection = DriverManager.getConnection(dburl, username, password);
                    System.out.println("New file name is " + fileName);
                    String sql = "DROP DATABASE " + nfilename;
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    statement.close();
                    DatabasesInfo databaseInfo = databasesInfoServices.getByDbname(fileName);
                    databasesInfoServices.delete(databaseInfo);
                    // smg = "Database created successfully";
                } catch (SQLException e) {

                    DatabasesInfo databaseInfo = databasesInfoServices.getByDbname(fileName);
                    databasesInfoServices.delete(databaseInfo);
                    //  smg = e.getMessage()+ "Mysql database already exist";
                    System.out.println("Error " + fileName +" "+e.getMessage());
                }
                // return smg;
            }
        }).start();

        return new ModelAndView("redirect:/database/master");
        // return "Done";
    }

}
