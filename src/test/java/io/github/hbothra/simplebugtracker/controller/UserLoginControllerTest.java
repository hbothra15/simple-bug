package io.github.hbothra.simplebugtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserLoginControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
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
