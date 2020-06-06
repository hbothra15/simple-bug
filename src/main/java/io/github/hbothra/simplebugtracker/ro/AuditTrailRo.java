package io.github.hbothra.simplebugtracker.ro;

import lombok.Data;

@Data
public class AuditTrailRo {

	private SimpleUserRo modifiedBy;
	private SimpleUserRo createdBy;
	private Long modifiedById;
	private Long createdById;
	private long version;
}
