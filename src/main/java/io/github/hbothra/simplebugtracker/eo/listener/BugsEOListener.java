package io.github.hbothra.simplebugtracker.eo.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.github.hbothra.simplebugtracker.eo.Bugs;

public class BugsEOListener {

	@PrePersist
	private void beforePersist(Bugs target) {
		if (null == target.getProject()) {
			target.setProject(0L);
		}
		if (null != target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
	}

	@PreUpdate
	private void beforeUpdate(Bugs target) {
		if (null != target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
	}
}
