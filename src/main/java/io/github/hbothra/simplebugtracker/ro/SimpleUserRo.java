package io.github.hbothra.simplebugtracker.ro;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect
public class SimpleUserRo extends AuditTrailRo {

	private Long userId;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String contact;
	private String email;
	private String password;
	private String passwordConfirm;
	private Set<UsersRoleRo> roles;
	
}
