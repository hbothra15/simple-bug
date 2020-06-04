package io.github.hbothra.simplebugtracker.ro;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect
public class BugCommentsRo extends AuditTrailRo {

	private Long commentId;
	private BugRo bug;
	private Long bugId;
	private String comments;
	private LocalDateTime modifiedOn;
	private LocalDateTime createdOn;
	
}
