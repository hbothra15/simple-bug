package io.github.hbothra.simplebugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.github.hbothra.user", "io.github.hbothra.simplebugtracker" })
@ConfigurationPropertiesScan("io.github.hbothra.simplebugtracker.messages")
public class SimpleBugTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBugTrackerApplication.class);
	}
}
