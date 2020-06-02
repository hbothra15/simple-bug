package io.github.hbothra.simplebugtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
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
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=create"
})
public class UserLoginImplTest {

	@Autowired
	private UserLoginImpl login;
	
	@Test
	// @formatter:off
	@SqlGroup({
		@Sql(scripts = {"classpath:user-data.sql"}),
		@Sql(scripts = {"classpath:user-data-cleanup.sql"}, 
			 executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
			 config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
		)
	})
	// @formatter:on
	public void testLoadUserByUsername() {
		UserDetails user = login.loadUserByUsername("admin@simpleBug.com");
		assertEquals("admin@simpleBug.com", user.getUsername());
		assertEquals("LetMeIn", user.getPassword());
	}
	
	@Test
	// @formatter:off
	@SqlGroup({
		@Sql(scripts = {"classpath:user-data.sql"}),
		@Sql(scripts = {"classpath:user-data-cleanup.sql"}, 
			 executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
			 config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
		)
	})
	// @formatter:on
	public void testLoadUserByInvalidUsername() {
		assertThrows(UsernameNotFoundException.class, () -> {
			login.loadUserByUsername("invalid@simpleBug.com");
		});
	}
}
