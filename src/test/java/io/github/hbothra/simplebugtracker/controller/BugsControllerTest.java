package io.github.hbothra.simplebugtracker.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.hbothra.simplebugtracker.eo.BugType;
import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.eo.StatusType;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugTypeRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;
import io.github.hbothra.simplebugtracker.repo.StatusTypeRepo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BugsControllerTest extends BaseController {

	@MockBean
	private BugsRepository bugRepo;
	
	@MockBean
	private BugsHistoryRepo hisRepo;
	
	@MockBean
	private BugTypeRepo bugTypeRepo;
	
	@MockBean
	private StatusTypeRepo statusRepo;
	
	@MockBean
	private BugCommentsRepo commRepo;

	private List<Bugs> mockedBugs;
	
	private List<BugsComments> mockedComments;
	
	private static final String BUG_TITLE="Mocked Bug";
	
	private static final String BUG_DESCR="Mocked Bug Description";
	
	// @formatter:off
	private static final String CREATE_BUG_JSON = "{"
			+ "\"title\": \"Title\","
			+ "\"descr\": \"A very long Description is expected over here\","
			+ "\"assignedToId\" : 1,"
			+ "\"bugStatus\" : \"TO_DO\","
			+ "\"bugType\" : \"REPAIR\""
			+ "}";
	// @formatter:on
	
	// @formatter:off
		private static final String UPDATE_BUG_JSON = "{"
				+ "\"title\": \"Title\","
				+ "\"descr\": \"A very long Description is expected over here\","
				+ "\"assignedToId\" : 2,"
				+ "\"bugId\" : 1"
				+ "}";
		// @formatter:on
	
	private void mockBugList(int noOfBugs) {
		assertTrue(noOfBugs>0, "Need to atleast 1 bug to add to list");
		mockedBugs = new ArrayList<>();
		
		BugType bugtype = new BugType();
		bugtype.setLookupValue("REPAIR");
		
		StatusType bugStatus = new StatusType();
		bugStatus.setLookupValue("TO_DO");
		
		IntStream.range(1, noOfBugs+1).forEach(count -> {
			Bugs bugs = new Bugs();
			bugs.setBugId(Long.valueOf(count));
			bugs.setTitle(BUG_TITLE+count);
			bugs.setDescr(BUG_DESCR+count);
			bugs.setBugType(bugtype);
			bugs.setBugStatus(bugStatus);
			mockedBugs.add(bugs);
		});
	}
	
	@Test
	public void testFindAll() throws Exception {
		mockBugList(1);
		when(bugRepo.findAll()).thenReturn(mockedBugs);
		
		String authToken = getToken(ROLE_ADMIN);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(jsonPath("$[0].bugId").value(1L))
					.andExpect(jsonPath("$[0].title").value(BUG_TITLE+1))
					.andExpect(jsonPath("$[0].descr").value(BUG_DESCR+1));
		
		authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isForbidden());
	}
	
	@Test
	public void testFetchAllBugsBasedOnUserId() throws Exception {
		mockBugList(2);
		when(bugRepo.findAllById(any())).thenReturn(mockedBugs);
		
		String authToken = getToken(ROLE_ADMIN);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/user").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isForbidden());
		
		authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/user").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isForbidden());
		
		authToken = getToken(ROLE_VENDOR);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/user").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(jsonPath("$[0].bugId").value(1L))
					.andExpect(jsonPath("$[0].title").value(BUG_TITLE+1))
					.andExpect(jsonPath("$[0].descr").value(BUG_DESCR+1))
					.andExpect(jsonPath("$[1].bugId").value(2L))
					.andExpect(jsonPath("$[1].title").value(BUG_TITLE+2))
					.andExpect(jsonPath("$[1].descr").value(BUG_DESCR+2));
		
		authToken = getToken(ROLE_SUPPORT);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/user").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(jsonPath("$[0].bugId").value(1L))
					.andExpect(jsonPath("$[0].title").value(BUG_TITLE+1))
					.andExpect(jsonPath("$[0].descr").value(BUG_DESCR+1))
					.andExpect(jsonPath("$[1].bugId").value(2L))
					.andExpect(jsonPath("$[1].title").value(BUG_TITLE+2))
					.andExpect(jsonPath("$[1].descr").value(BUG_DESCR+2));
	}
	
	@Test
	public void testGetBugComments() throws Exception {
		mockBugList(1);
		mockedComments = new ArrayList<>();
		BugsComments comment1 = new BugsComments();
		comment1.setBug(mockedBugs.get(0));
		comment1.setCommentId(1L);
		comment1.setComments("Comment");
		comment1.setCreatedById(1L);
		mockedComments.add(comment1);
		
		when(commRepo.findAllByBugId(1L)).thenReturn(mockedComments);
		
		String authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/1/comments").header("Authorization", "Bearer " + authToken))
			.andExpect(status().isForbidden());
		
		authToken = getToken(ROLE_ADMIN);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs/1/comments").header("Authorization", "Bearer " + authToken))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$[0].commentId").value(1L))
			.andExpect(jsonPath("$[0].bug.title").value(BUG_TITLE+1))
			.andExpect(jsonPath("$[0].bug.descr").value(BUG_DESCR+1))
			.andExpect(jsonPath("$[0].bug.bugId").value(1L))
			.andExpect(jsonPath("$[0].comments").value("Comment"))
			.andExpect(jsonPath("$[0].createdById").value(1L));
	}
	
	@Test
	public void testAddBug() throws Exception {
		mockBugList(1);
		when(bugRepo.saveAndFlush(any())).thenReturn(mockedBugs.get(0));
		when(bugTypeRepo.findByLookupValue("REPAIR")).thenReturn(Optional.of(new BugType()));
		when(statusRepo.findByLookupValue("TO_DO")).thenReturn(Optional.of(new StatusType()));
		
		String authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.post("/api/bugs").header("Authorization", "Bearer " + authToken).contentType(MediaType.APPLICATION_JSON).content(CREATE_BUG_JSON))
			.andExpect(status().isForbidden());
		
		authToken = getToken(ROLE_VENDOR);
		mvc.perform(MockMvcRequestBuilders.post("/api/bugs").header("Authorization", "Bearer " + authToken).contentType(MediaType.APPLICATION_JSON).content(CREATE_BUG_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.bugId").value(1L))
			.andExpect(jsonPath("$.title").value(BUG_TITLE+1))
			.andExpect(jsonPath("$.descr").value(BUG_DESCR+1));
	}
	
	@Test
	public void testUpdateBug() throws Exception {
		mockBugList(1);
		mockedBugs.get(0).setCreatedById(1L);
		when(bugRepo.findById(1L)).thenReturn(Optional.of(mockedBugs.get(0)));
		when(bugRepo.saveAndFlush(any(Bugs.class))).thenAnswer(i -> i.getArgument(0));
		when(bugTypeRepo.findByLookupValue("REPAIR")).thenReturn(Optional.of(mockedBugs.get(0).getBugType()));
		when(statusRepo.findByLookupValue("TO_DO")).thenReturn(Optional.of(mockedBugs.get(0).getBugStatus()));
		
		String authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.put("/api/bugs").header("Authorization", "Bearer " + authToken).contentType(MediaType.APPLICATION_JSON).content(CREATE_BUG_JSON))
			.andExpect(status().isForbidden());
		
		authToken = getToken(ROLE_VENDOR);
		mvc.perform(MockMvcRequestBuilders.put("/api/bugs").header("Authorization", "Bearer " + authToken).contentType(MediaType.APPLICATION_JSON).content(UPDATE_BUG_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.bugId").value(1L))
			.andExpect(jsonPath("$.title").value("Title"))
			.andExpect(jsonPath("$.descr").value("A very long Description is expected over here"))
			.andExpect(jsonPath("$.assignedToId").value(2L))
			.andExpect(jsonPath("$.bugStatus").value("TO_DO"))
			.andExpect(jsonPath("$.bugType").value("REPAIR"));
	}
}
