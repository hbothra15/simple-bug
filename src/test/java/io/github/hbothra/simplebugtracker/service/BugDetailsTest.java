package io.github.hbothra.simplebugtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import io.github.hbothra.simplebugtracker.eo.Bugs;

import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=create"
})
public class BugDetailsTest {

	@Autowired
	private BugDetails details;
	
	@Test
	// @formatter:off
	@SqlGroup({
		@Sql(scripts = {"classpath:bug-data.sql"}),
		@Sql(scripts = {"classpath:bug-data-cleanup.sql"}, 
			 executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
			 config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
		)
	})
	// @formatter:on
	public void testFetchAllbugsBasedOnUserId() {
		List<Bugs> adminBugs = details.fetcchAllBugsBasedOnUserId(1);
		List<Bugs> supportBugs = details.fetcchAllBugsBasedOnUserId(2);
		List<Bugs> vendorBugs = details.fetcchAllBugsBasedOnUserId(3);
		
		assertEquals(3, adminBugs.size());
		assertEquals(2, supportBugs.size());
		assertEquals(2, vendorBugs.size());
	}
	
}
