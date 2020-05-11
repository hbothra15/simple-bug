package io.github.hbothra.simplebugtracker.eo.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.github.hbothra.simplebugtracker.eo.AuditTrail;

public class AuditTrailListener {

	@PrePersist
	private void beforePersist(AuditTrail audit) {
		audit.setCreatedOn(LocalDateTime.now());
		audit.setModifiedOn(LocalDateTime.now());
	}
	
	@PreUpdate
	private void beforeUpdate(AuditTrail audit) {
		audit.setModifiedOn(LocalDateTime.now());
	}
}
