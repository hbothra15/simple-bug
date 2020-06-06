package io.github.hbothra.simplebugtracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;
import io.github.hbothra.simplebugtracker.ro.BugRo;
import io.github.hbothra.simplebugtracker.ro.SimpleUserRo;

@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("test")
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=update" })
@TestMethodOrder(OrderAnnotation.class)
public class BugServiceTest {

	@Autowired
	private BugService details;

	@Test
	@Order(1)
	@Sql(scripts = { "classpath:bug-data.sql" })
	public void dataSetUp() {
		assertTrue(true, "To Make Sure We have all the required data in place");
	}

	@Test
	@Order(2)
	public void testFetchAllbugsBasedOnUserId() {
		List<BugRo> adminBugs = details.fetchAllBugsBasedOnUserId(1);
		List<BugRo> supportBugs = details.fetchAllBugsBasedOnUserId(2);
		List<BugRo> vendorBugs = details.fetchAllBugsBasedOnUserId(3);

		assertEquals(3, adminBugs.size());
		assertEquals(2, supportBugs.size());
		assertEquals(2, vendorBugs.size());
	}

	@Test
	@Order(3)
	public void testFindAll() {
		List<BugRo> adminBugs = details.findAll();
		assertEquals(3, adminBugs.size());
	}

	@Test
	@Order(4)
	public void testGetAllComments() {
		List<BugCommentsRo> comments = details.getAllComments(1L);
		assertEquals(1, comments.size());
		assertEquals("Test Comment", comments.get(0).getComments());
	}

	@Test
	@Order(5)
	public void testAddBug() {
		LocalDateTime currentTime = LocalDateTime.now();
		SimpleUserRo userRo = new SimpleUserRo();
		userRo.setUserId(1L);

		BugRo bugRo = new BugRo();
		bugRo.setAssignedTo(userRo);
		bugRo.setModifiedById(1L);
		bugRo.setCreatedById(1L);
		bugRo.setBugStatus("TO_DO");
		bugRo.setBugType("REPAIR");
		bugRo.setDescr("Descr");
		bugRo.setTitle("Title");

		BugRo actual = details.addBug(bugRo);

		assertEquals("REPAIR", actual.getBugType());
		assertEquals("TO_DO", actual.getBugStatus());
		assertEquals("Title", actual.getTitle());
		assertEquals("Descr", actual.getDescr());
		assertNotNull(actual.getBugId());
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getCreatedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getModifiedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertEquals("ADMIN", actual.getAssignedTo().getName());
		assertEquals(0L, actual.getVersion());
	}
	
	@Test
	@Order(6)
	public void testAddBugWithoutAssignedToUserDetails() {
		LocalDateTime currentTime = LocalDateTime.now();

		BugRo bugRo = new BugRo();
		bugRo.setAssignedToId(1L);
		bugRo.setModifiedById(1L);
		bugRo.setCreatedById(1L);
		bugRo.setBugStatus("TO_DO");
		bugRo.setBugType("REPAIR");
		bugRo.setDescr("Descr");
		bugRo.setTitle("Title");
		bugRo.setProject(1L);

		BugRo actual = details.addBug(bugRo);

		assertEquals("REPAIR", actual.getBugType());
		assertEquals("TO_DO", actual.getBugStatus());
		assertEquals("Title", actual.getTitle());
		assertEquals("Descr", actual.getDescr());
		assertNotNull(actual.getBugId());
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getCreatedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getModifiedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertNull(actual.getAssignedTo());
		assertEquals(1L, actual.getProject());
		assertEquals(0L, actual.getVersion());
	}
	
	@Test
	@Order(7)
	public void testAddBugWithoutAssignedToUserDetailsId() {
		LocalDateTime currentTime = LocalDateTime.now();
		SimpleUserRo userRo = new SimpleUserRo();
		
		BugRo bugRo = new BugRo();
		bugRo.setAssignedTo(userRo);
		bugRo.setModifiedById(1L);
		bugRo.setCreatedById(1L);
		bugRo.setBugStatus("TO_DO");
		bugRo.setBugType("REPAIR");
		bugRo.setDescr("Descr");
		bugRo.setTitle("Title");
		bugRo.setProject(1L);

		BugRo actual = details.addBug(bugRo);

		assertEquals("REPAIR", actual.getBugType());
		assertEquals("TO_DO", actual.getBugStatus());
		assertEquals("Title", actual.getTitle());
		assertEquals("Descr", actual.getDescr());
		assertNotNull(actual.getBugId());
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getCreatedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertTrue(currentTime.truncatedTo(ChronoUnit.MINUTES)
				.isEqual(actual.getModifiedOn().truncatedTo(ChronoUnit.MINUTES)));
		assertNull(actual.getAssignedTo());
		assertEquals(1L, actual.getProject());
		assertEquals(0L, actual.getVersion());
	}

	@Test
	@Order(99)
	@Sql(scripts = {
			"classpath:data-cleanup.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	public void dataCleanUp() {
		assertTrue(true, "To Make Sure Data gets wiped out");
	}
}
