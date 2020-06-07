package io.github.hbothra.simplebugtracker.messages;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "user.message")
@Getter
@Setter
public class UserMessages {

	private String enityNotFound;
}
