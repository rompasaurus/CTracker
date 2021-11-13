package com.CTracker;

import com.CTracker.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication
//enabled async for the mailservice
@EnableAsync
@Import(SwaggerConfiguration.class)
@EnableCaching
@EnableJpaRepositories
public class CoasterTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoasterTrackerApplication.class, args);
	}

}
