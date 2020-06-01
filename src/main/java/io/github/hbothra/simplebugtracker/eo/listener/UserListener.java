package io.github.hbothra.simplebugtracker.eo.listener;

import javax.persistence.PrePersist;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;

public class UserListener {

	@PrePersist
	private void beforePersist(SimpleUser target) {
		if(null == target.getPassword()) {
			target.setPassword("Welcome1!");
		}
	}
}
