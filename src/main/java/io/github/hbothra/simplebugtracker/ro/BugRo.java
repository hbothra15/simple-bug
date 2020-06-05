package io.github.hbothra.simplebugtracker.ro;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect
public class BugRo extends AuditTrailRo {

	private Long bugId;
	private Long project;
	private String title;
	private String descr;
	private String bugType;
	private String bugStatus ;
	private SimpleUserRo assignedTo;
	private Long assignedToId;
	private LocalDateTime modifiedOn;
	private LocalDateTime createdOn;

}
