package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"modifiedOn"}, allowGetters = true)
@MappedSuperclass
public class BugBase extends AuditTrail {
		
	@Column(name="BUG_TITLE", nullable = false, length = 50)
	private String title;
	
	@Column(name="BUG_DESCR", nullable = false)
	@Lob
	private String descr;
		
	@JoinColumn(name="BUG_TYPE", nullable = false)
	@ManyToOne
	private BugType bugType;
	
	@JoinColumn(name="BUG_STATUS", nullable = false)
	@ManyToOne
	private StatusType bugStatus;
	
	@JoinColumn(name="ASSIGNED_TO",insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User assignedTo;
	
	@Column(name = "ASSIGNED_TO")
	private Long assignedToId;
}
