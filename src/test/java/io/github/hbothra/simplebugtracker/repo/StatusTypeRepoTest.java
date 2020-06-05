package io.github.hbothra.simplebugtracker.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import io.github.hbothra.simplebugtracker.eo.StatusType;

@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("test")
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=create" })
@TestMethodOrder(OrderAnnotation.class)
public class StatusTypeRepoTest {

	@Autowired
	private StatusTypeRepo repo;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;

	@Test
	@Order(1)
	public void injectedComponentsAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(repo);
	}

	@Test
	@Order(2)
	public void testSave() {
		StatusType type = new StatusType();
		type.setLookupValue("NEW_TYPE");

		StatusType expected = repo.save(type);
		assertNotNull(expected.getLookupCode());
	}

	@Test
	@Order(3)
	public void testFindByLookupValue() {
		Optional<StatusType> expected = repo.findByLookupValue("NEW_TYPE");
		assertTrue(expected.isPresent());
	}

	@Test
	@Order(99)
	@Sql(scripts = {
			"classpath:data-cleanup.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	public void dataCleanUp() {
		assertTrue(true, "To Make Sure Data gets wiped out");
	}
}
