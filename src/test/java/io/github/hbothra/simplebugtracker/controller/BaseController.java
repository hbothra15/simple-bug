package io.github.hbothra.simplebugtracker.controller;

import java.util.Arrays;
import java.util.Optional;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.eo.UserType;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=none" })
public class BaseController {

	@MockBean
	protected UserRepo userRepo;

	@Autowired
	protected MockMvc mvc;
	
	protected static final String USER_PASSWRD = "password";

	protected static final String USER_NAME = "user";

	protected static final String ROLE_ADMIN = "ADMIN";
	
	protected static final String ROLE_VENDOR = "VENDOR";
	
	protected static final String ROLE_SUPPORT = "SUPPORT";

	protected static final String ROLE_VIEW = "";
	
	public SimpleUser buildUser(String role) {
		SimpleUser user = new SimpleUser();
		UserType usrRole = new UserType();
		usrRole.setLookupCode(1L);
		usrRole.setLookupValue(role);
		user.setEmail(USER_NAME);
		user.setPassword(USER_PASSWRD);
		user.setUserId(1L);
		user.addRole(usrRole);
		return user;
	}
	
	protected String getToken(String role) throws Exception {
		SimpleUser user = buildUser(role);
		Mockito.when(userRepo.findAll(Mockito.any(Sort.class))).thenReturn(Arrays.asList(user));
		Mockito.when(userRepo.findByUserName(USER_NAME)).thenReturn(Optional.of(user));
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/login").param("userName", USER_NAME).param("pass", USER_PASSWRD))
				.andReturn();
		return result.getResponse().getContentAsString();
	}
	
}
