package io.github.hbothra.simplebugtracker.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest extends BaseController {

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
}
