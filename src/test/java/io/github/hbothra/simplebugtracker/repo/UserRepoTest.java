package io.github.hbothra.simplebugtracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.eo.UserType;

@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("test")
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=create-drop" })
public class UserRepoTest {

	@Autowired
	private UserRepo repo;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;

	@Test
	public void injectedComponentsAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(repo);
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
	public void testFindByUserName() {
		com.github.hbothra.user.entity.User user = repo.findByUserName("admin@simpleBug.com").get();
		assertTrue(user instanceof SimpleUser, "User should be instance of EO User");
		assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
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
	public void testFindByEmail() {
		SimpleUser user = repo.findByEmail("admin@simpleBug.com").get();
		assertEquals("ADMIN", user.getName());
		List<UserType> userRole = new ArrayList<UserType>(user.getRoles());
		assertEquals("ADMIN", userRole.get(0).getLookupValue());
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
	public void testFindByContact() {
		SimpleUser user = repo.findByContact("7700000000").get();
		assertEquals("ADMIN", user.getName());
	}
}
