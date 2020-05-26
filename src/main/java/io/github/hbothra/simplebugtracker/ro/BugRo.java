package io.github.hbothra.simplebugtracker.ro;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BugRo extends AuditTrailRo {

	private Long bugId;
	private String title, descr;
	private BugTypeRo bugType;
	private StatusRo bugStatus ;
	private SimpleUserRo assignedTo;
	private Long assignedToId;
	private LocalDateTime modifiedOn,createdOn;

}
