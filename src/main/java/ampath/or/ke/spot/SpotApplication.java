package ampath.or.ke.spot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class SpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotApplication.class, args);
	}

}
