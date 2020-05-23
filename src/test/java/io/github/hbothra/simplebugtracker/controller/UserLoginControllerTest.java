package io.github.hbothra.simplebugtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.eo.UserRole;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserLoginControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UserRepo userRepo;
	
	@BeforeEach
	public void setup () {
		User user = new User();
		UserRole role = new UserRole();
		role.setId(1L);
		role.setType("ADMIN");
		user.setEmail("admin@simpleBug.com");
		user.setPassword("LetMeIn");
		user.setUserId(1L);
		user.addRole(role);
		Mockito.when(userRepo.findByUserName("admin@simpleBug.com")).thenReturn(Optional.of(user));
	}
	
	@Test
	public void enableLogin() throws Exception {
		ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:"+port+"/login?userName=admin@simpleBug.com&pass=LetMeIn",null, String.class);
		assertEquals(HttpStatus.OK,result.getStatusCode());
		assertNotNull(result.getBody());
	}
	
	@Test
	public void unableLogin() throws Exception {
		ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:"+port+"/login?userName=admin@simpleBug.com&pass=LetMeOut",null, String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
	}
	
}
