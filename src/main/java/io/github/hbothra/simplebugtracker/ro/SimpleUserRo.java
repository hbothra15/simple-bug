package io.github.hbothra.simplebugtracker.ro;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleUserRo extends AuditTrailRo {

	private Long userId;
	private String name, addressLine1, addressLine2, addressLine3, contact, email, password, passwordConfirm;
	private Set<UsersRoleRo> roles;
	
}
