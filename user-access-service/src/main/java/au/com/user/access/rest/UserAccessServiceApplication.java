package au.com.user.access.rest;

import au.com.user.access.service.UserAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class UserAccessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccessServiceApplication.class, args);
	}

	@Bean
	public UserAccessService userAccessService() {
		return new UserAccessService();
	}

}
