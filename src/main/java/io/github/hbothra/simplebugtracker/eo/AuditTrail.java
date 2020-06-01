package io.github.hbothra.simplebugtracker.eo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import io.github.hbothra.simplebugtracker.eo.listener.AuditTrailListener;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditTrailListener.class)
@Data
public class AuditTrail {

	@Version
	@Column(name="MODIFIED_ON", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime modifiedOn;
	
	@JoinColumn(name="MODIFIED_BY",insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private SimpleUser modifiedBy;
	
	@Column(name = "MODIFIED_BY")
	private Long modifiedById;
	
	@Column(name="CREATED_ON", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdOn;
	
	@JoinColumn(name="CREATED_BY", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private SimpleUser createdBy;
	
	@Column(name = "CREATED_BY", updatable = false)
	private Long createdById;
}
