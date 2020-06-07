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
		if(null == audit.getCreatedById() && null != audit.getCreatedBy()) {
			audit.setCreatedById(audit.getCreatedBy().getUserId());
		}
		
		if(null == audit.getModifiedById() && null != audit.getModifiedBy()) {
			audit.setModifiedById(audit.getModifiedBy().getUserId());
		}
	}
	
	@PreUpdate
	private void beforeUpdate(AuditTrail audit) {
		audit.setModifiedOn(LocalDateTime.now());
		if(null == audit.getModifiedById() && null != audit.getModifiedBy()) {
			audit.setModifiedById(audit.getModifiedBy().getUserId());
		}
	}
}
