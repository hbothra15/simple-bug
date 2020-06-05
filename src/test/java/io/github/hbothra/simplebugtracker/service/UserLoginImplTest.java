package io.github.hbothra.simplebugtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=create"
})
@TestMethodOrder(OrderAnnotation.class)
public class UserLoginImplTest {

	@Autowired
	private UserLoginImpl login;
	
	@Test
	@Order(1)
	@Sql(scripts = {"classpath:user-data.sql"})
	public void dataSetUp() {
		assertTrue(true, "To Make Sure We have all the required data in place");
	}
	
	@Test
	@Order(2)
	public void testLoadUserByUsername() {
		UserDetails user = login.loadUserByUsername("admin@simpleBug.com");
		assertEquals("admin@simpleBug.com", user.getUsername());
		assertEquals("LetMeIn", user.getPassword());
	}
	
	@Test
	@Order(3)
	public void testLoadUserByInvalidUsername() {
		assertThrows(UsernameNotFoundException.class, () -> {
			login.loadUserByUsername("invalid@simpleBug.com");
		});
	}
	
	@Test
	@Order(99)
	@Sql(scripts = {
			"classpath:data-cleanup.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	public void dataCleanUp() {
		assertTrue(true, "To Make Sure Data gets wiped out");
	}
}
