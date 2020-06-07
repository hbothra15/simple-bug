package io.github.hbothra.simplebugtracker.messages;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "bug.message")
@Setter
@Getter
public class BugMessages {

	private String entityNotFound;
	private String modifiedByNotFound;
	private String createdByNotFound;
	private String bugIdNotFound;
	private String bugVersionNotFound;
	private String bugTypeNotFound;
	private String bugStatusNotFound;

}