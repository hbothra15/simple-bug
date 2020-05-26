package io.github.hbothra.simplebugtracker.ro;

import lombok.Data;

@Data
public class AuditTrailRo {

	private SimpleUserRo modifiedBy, createdBy;
	private Long modifiedById,createdById;
	
}
