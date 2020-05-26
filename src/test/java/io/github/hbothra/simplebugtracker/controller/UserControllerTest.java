package io.github.hbothra.simplebugtracker.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.eo.UserType;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	@MockBean
	private UserRepo userRepo;

	@Autowired
	private MockMvc mvc;

	private static final String USER_PASSWRD = "password";

	private static final String USER_NAME = "user";

	private static final String ROLE_ADMIN = "ADMIN";

	private static final String ROLE_VIEW = "";

	// @formatter:off
	private static final String CREATE_USER_JSON = "{"
			+ "\"name\": \"user\","
			+ "\"addressLine1\": \"Address Line 1\","
			+ "\"addressLine2\" : \"Address Line 2\","
			+ "\"email\" : \""+ USER_NAME +"\","
			+ "\"contact\" : \"9876543210\","
			+ "\"password\" : \""+ USER_PASSWRD +"\","
			+ "\"passwordConfirm\" : \""+ USER_PASSWRD +"\","
			+ "\"roles\":"
			+ "	[{"
			+ "		\"id\":1,"
			+ "		\"type\":\""+ROLE_ADMIN + "\""
			+ "	}]"
			+ "}";
	// @formatter:on
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

	@Test
	public void testGetAllUsers() throws Exception {
		String url = "/api/users";
		String authToken = getToken(ROLE_ADMIN);
		mvc.perform(MockMvcRequestBuilders.get(url).header("Authorization", "Bearer " + authToken))
				.andExpect(status().isOk());
		authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.get(url).header("Authorization", "Bearer " + authToken))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testAddUsers() throws Exception {
		String url = "/api/users";
		String authToken = getToken(ROLE_ADMIN);

		Mockito.when(userRepo.save(Mockito.any(SimpleUser.class))).thenReturn(buildUser(ROLE_ADMIN));

		mvc.perform(MockMvcRequestBuilders.post(url).header("Authorization", "Bearer " + authToken)
				.contentType(MediaType.APPLICATION_JSON).content(CREATE_USER_JSON)).andExpect(status().isOk());
		authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.post(url).header("Authorization", "Bearer " + authToken)
				.contentType(MediaType.APPLICATION_JSON).content(CREATE_USER_JSON)).andExpect(status().isForbidden());
	}

	private String getToken(String role) throws Exception {
		SimpleUser user = buildUser(role);
		Mockito.when(userRepo.findAll(Mockito.any(Sort.class))).thenReturn(Arrays.asList(user));
		Mockito.when(userRepo.findByUserName(USER_NAME)).thenReturn(Optional.of(user));
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/login").param("userName", USER_NAME).param("pass", USER_PASSWRD))
				.andReturn();
		return result.getResponse().getContentAsString();
	}
}
