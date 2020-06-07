package io.github.hbothra.simplebugtracker.ro;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class UsersRoleRo {

	private Long roleId;
	private String role;

}
