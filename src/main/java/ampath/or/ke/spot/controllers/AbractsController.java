package ampath.or.ke.spot.controllers;

import ampath.or.ke.spot.models.Abstracts;
import ampath.or.ke.spot.models.AfyastatErrors;
import ampath.or.ke.spot.models.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;
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
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public ModelAndView list_errors(HttpSession session) throws IOException, JSONException, SQLException {

            ModelAndView modelAndView = new ModelAndView();
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
            String newFilename = abstracts.getCategory()+"-"+ tracker + extension;
            String destFilePath = data_path + "abstracts" + File.separator + newFilename;

            File destFile = new File(destFilePath);
            boolean exists = destFile.exists();
            System.out.println("File exists: " + exists + " Tracker: " + tracker);
            Date nowDate = new Date();
            abstracts.setTrackerNo(newFilename);
            abstracts.setDateCreated(nowDate);

            if (exists) {
                destFile.deleteOnExit();
            } else {
                try {
                    // Transfer the uploaded file to the destination
                    file.transferTo(destFile);
                    Ab
                    System.out.println("File transferred successfully.");
                } catch (IOException e) {
                    // Handle the exception if file transfer fails
                    System.out.println("Error transferring file: " + e.getMessage());
                }
            }
        } else {
            System.out.println("You failed to upload " + fileName
                    + " because the file was empty.");
        }
        modelAndView.addObject("abstracts", new Abstracts());
        modelAndView.setViewName("abstracts/call");
        return modelAndView;


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
