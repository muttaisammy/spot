package ampath.or.ke.spot.tasks;

import ampath.or.ke.spot.models.AppointmentsTracker;
import ampath.or.ke.spot.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

public class AppointmentsTask {
    @Value("${spring.etl.username}")
    public String username;
    @Value("${spring.etl.password}")
    public String password;
    @Value("${spring.etl.server}")
    public String server;

    @Autowired
    private AppointmentsService appointmentsService;

    public void ProcessErrors() throws  ParseException, SQLException, IOException {
        List<AppointmentsTracker> appointmentsTrackerList = appointmentsService.getAll();
        Connection con = DriverManager.getConnection(server, username, password);

        for(int x=0;x<appointmentsTrackerList.size();x++){

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            int id = appointmentsTrackerList.get(x).getId();
            ResultSet rs = stmt.executeQuery("SELECT * FROM etl.sms_delivery_report;");
            rs.last();
            x = rs.getRow();

            rs.beforeFirst();
            while (rs.next()) {
                String  luuid = rs.getString(1).toString();
            }

        }
        con.close();



    }

}
