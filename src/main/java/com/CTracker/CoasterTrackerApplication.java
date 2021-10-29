package com.CTracker;

import com.CTracker.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication
//enabled async for the mailservice
@EnableAsync
@Import(SwaggerConfiguration.class)
public class CoasterTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoasterTrackerApplication.class, args);
	}

}
