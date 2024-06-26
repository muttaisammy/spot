package ampath.or.ke.spot;

import ampath.or.ke.spot.controllers.Rest.ApiKeyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class SpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
		FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ApiKeyFilter());
		registrationBean.addUrlPatterns("/rest/v1/api/*"); // Set the URL patterns to secure
		return registrationBean;
	}
}
