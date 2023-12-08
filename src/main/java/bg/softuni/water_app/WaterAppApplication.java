package bg.softuni.water_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableMethodSecurity(
		prePostEnabled = true)
@SpringBootApplication
public class WaterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterAppApplication.class, args);
	}

}
