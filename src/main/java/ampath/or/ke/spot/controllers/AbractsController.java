package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.AfyastatErrors;
import ampath.or.ke.spot.models.SMTPServer;
import ampath.or.ke.spot.models.User;
import ampath.or.ke.spot.services.AbstractService;
import ampath.or.ke.spot.services.AfyastatClientLineListService;
import ampath.or.ke.spot.services.SMTPServerService;
import ampath.or.ke.spot.utils.HtmlEmails;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@Transactional
@RequestMapping("/abstracts")
public class AbractsController {

    @Value("${app.dir}")
    public String data_path;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private AbstractService abstractService;
    @Autowired
    private SMTPServerService smtpServerService;

    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public ModelAndView list_errors(HttpSession session) throws IOException, JSONException, SQLException {

            ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("smg", 0);
            modelAndView.addObject("abstracts", new Abstracts());
            modelAndView.setViewName("abstracts/call");
            return modelAndView;


    }
    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public ModelAndView call(@Valid Abstracts abstracts , BindingResult bindingResult,@RequestParam("abstractFile") MultipartFile file) throws IOException, JSONException, SQLException {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(abstracts.getTitle());
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            // Get the original filename
            String originalFilename = file.getOriginalFilename();
            // Get the file extension
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // Generate a tracker number (Assuming this method exists)
            String tracker = generateTrackerNumber();

            // Construct the new filename with the tracker number
            String trackerNo = abstracts.getCategory()+"-"+ tracker;
            String newFilename = abstracts.getCategory()+"-"+ tracker + extension;
            String destFilePath = data_path + "abstracts" + File.separator + newFilename;

            File destFile = new File(destFilePath);
            boolean exists = destFile.exists();
            System.out.println("File exists: " + exists + " Tracker: " + tracker);
            Date nowDate = new Date();
            abstracts.setTrackerNo(trackerNo);
            abstracts.setDateCreated(nowDate);
            abstracts.setFileName(newFilename);

            if (exists) {
                destFile.deleteOnExit();
            } else {
                try {
                    // Transfer the uploaded file to the destination
                    file.transferTo(destFile);

                    abstractService.save(abstracts);
                    SMTPServer smtpServers = smtpServerService.getByTopOne();
                    //System.out.print(" Host ndo hii "+host);
                    if(smtpServers !=null) {
                        String subject = "Abstract Submission Successful";
                        String fname = abstracts.getMainauthor();
                        String atitle= abstracts.getTitle();
                        String coauthor =abstracts.getCoauthor();

                        String message = HtmlEmails.generateAuthorsubmit(fname,coauthor, trackerNo,atitle);

                        ApplicationMailer.sendMail(abstracts.getEmail(), subject, message, smtpServers);
                    }
                   // Post to managers
                    if(smtpServers !=null) {

                        String subject = "Abstract Received From "+ abstracts.getMainauthor();

                        String link= "https://spot.ampath.or.ke/abstracts/download/"+abstracts.getFileName();
                        String fname ="Team";
                        String author=abstracts.getMainauthor();
                        String coauthor = abstracts.getCoauthor();
                        String atitle = abstracts.getTitle();
                        String message = HtmlEmails.generateReviewersubmit(fname,  link, author, coauthor, trackerNo,atitle);

                        String[] recipients = { "erugut@ampath.or.ke", "nsewe@ampath.or.ke", "dgaitho@usaidampathuzima.or.ke","akingori@ampath.or.ke" };
                        for (String recipient : recipients) {
                            ApplicationMailer.sendMail(recipient, subject, message, smtpServers);
                           // message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                        }
                       // String to = "erugut@ampath.or.ke"+","+"nsewe@ampath.or.ke"+","+ "dgaitho@usaidampathuzima.or.ke"+","+"akingori@ampath.or.ke";
                       // ApplicationMailer.sendMail(to, subject, message, smtpServers);
                        //ApplicationMailer.sendMail(to, subject, message, smtpServers);
                    }
                    modelAndView.addObject("smg", 1);

                    System.out.println("File transferred successfully.");
                } catch (IOException e) {
                    // Handle the exception if file transfer fails
                    modelAndView.addObject("smg", 2);
                    System.out.println("Error transferring file: " + e.getMessage());
                }
            }
        } else {
            modelAndView.addObject("smg", 2);
            System.out.println("You failed to upload " + fileName
                    + " because the file was empty.");
        }
        modelAndView.addObject("abstracts", new Abstracts());
        modelAndView.setViewName("abstracts/call");
        return modelAndView;


    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(data_path + "abstracts/" + fileName);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    public String generateTrackerNumber() {
        // Get current timestamp
        long timestamp = System.currentTimeMillis();

        // Generate a random portion (6 digits)
        Random random = new Random();
        int randomPart = 100000 + random.nextInt(900000); // Ensures a 6-digit number

        // Combine timestamp and random portion
        String trackerNumber = formatTimestamp(timestamp) + "-" + randomPart;

        return trackerNumber;
    }

    private String formatTimestamp(long timestamp) {
        // Format timestamp as desired (e.g., yyyyMMddHHmmss)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date(timestamp));
    }
}
