package nl.stagesync.stagesync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class StageSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(StageSyncApplication.class, args);
	}

}
