package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class BugBase extends AuditTrail {
		
	@Column(name="BUG_TITLE", nullable = false, length = 50)
	private String title;
	
	@Column(name="BUG_DESCR", nullable = false)
	@Lob
	private String descr;
		
	@JoinColumn(name="BUG_TYPE", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private BugType bugType;
	
	@JoinColumn(name="BUG_STATUS", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private StatusType bugStatus;
	
	@JoinColumn(name="ASSIGNED_TO",insertable = false, updatable = false, nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private SimpleUser assignedTo;
	
	@Column(name = "ASSIGNED_TO", nullable = true)
	private Long assignedToId;
}
