package io.github.hbothra.simplebugtracker.eo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"modifiedOn"}, allowGetters = true)
@MappedSuperclass
public class BugBase {
		
	@Column(name="BUG_TITLE", nullable = false, length = 50)
	private String title;
	
	@Column(name="BUG_DESCR", nullable = false)
	@Lob
	private String descr;
	
	@JoinColumn(name="MODIFIED_BY", nullable = false)
	@ManyToOne
	private User modifiedBy;
	
	@Column(name="MODIFIED_ON", nullable = false)
	private LocalDateTime modifiedOn;
	
	@JoinColumn(name="BUG_TYPE", nullable = false)
	@ManyToOne
	private BugType bugType;
	
	@JoinColumn(name="BUG_STATUS", nullable = false)
	@ManyToOne
	private StatusType bugStatus;
}
