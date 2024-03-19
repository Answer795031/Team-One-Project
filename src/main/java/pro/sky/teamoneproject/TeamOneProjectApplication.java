package pro.sky.teamoneproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TeamOneProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(TeamOneProjectApplication.class, args);
	}
}