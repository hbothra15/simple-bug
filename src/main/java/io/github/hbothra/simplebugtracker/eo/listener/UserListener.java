package io.github.hbothra.simplebugtracker.eo.listener;

import javax.persistence.PrePersist;

import io.github.hbothra.simplebugtracker.eo.User;

public class UserListener {

	@PrePersist
	private void beforePersist(User target) {
		if(null == target.getPassword()) {
			target.setPassword("Welcome1!");
		}
	}
}
