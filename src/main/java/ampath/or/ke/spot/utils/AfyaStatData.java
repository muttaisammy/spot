package ampath.or.ke.spot.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class AfyaStatData {

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

    @Value("${spring.metabase.api.url}")
    private String strURL;
    @Value("${spring.metabase.api.username}")
    private String strUsername;

    @Value("${spring.metabase.api.password}")
    private String strPassword;

    @Value("${spring.metabase.api.queryId}")
    private String strQueryId;

    @Value("${spring.afyastat.server}")
    private String server;

    @Value("${spring.afyastat.username}")
    private String username;

    @Value("${spring.afyastat.password}")
    private String password;

    public void pullAllHTSFromDatabase() throws ParseException, SQLException, IOException {
    }
}
