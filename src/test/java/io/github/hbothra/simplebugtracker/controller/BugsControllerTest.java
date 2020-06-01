package io.github.hbothra.simplebugtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BugsControllerTest extends BaseController {

	@MockBean
	private BugsRepository bugRepo;
	
	@MockBean
	private BugsHistoryRepo hisRepo;
	
	@MockBean
	private BugCommentsRepo commRepo;

	private List<Bugs> mockedBugs;
	
	private List<BugsComments> mockedComments;
	
	private static final String BUG_TITLE="Mocked Bug";
	
	private static final String BUG_DESCR="Mocked Bug Description";
	
	
	@Test
	public void testFindAll() throws Exception {
		mockedBugs = new ArrayList<>();
		Bugs bugs = new Bugs();
		bugs.setBugId(1L);
		bugs.setTitle(BUG_TITLE);
		bugs.setDescr(BUG_DESCR);
		mockedBugs.add(bugs);
		when(bugRepo.findAll()).thenReturn(mockedBugs);
		
		String authToken = getToken(ROLE_ADMIN);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(jsonPath("$[0].bugId").value(1L))
					.andExpect(jsonPath("$[0].title").value(BUG_TITLE))
					.andExpect(jsonPath("$[0].descr").value(BUG_DESCR));
		
		authToken = getToken(ROLE_VIEW);
		mvc.perform(MockMvcRequestBuilders.get("/api/bugs").header("Authorization", "Bearer " + authToken))
					.andExpect(status().isForbidden());
	}
	
	@Test
	public void testFetchAllBugsBasedOnUserId() throws Exception {
		mockedBugs = new ArrayList<>();
		Bugs bug1 = new Bugs();
		bug1.setBugId(1L);
		bug1.setTitle(BUG_TITLE+1);
		bug1.setDescr(BUG_DESCR+1);
		Bugs bug2 = new Bugs();
		bug2.setBugId(2L);
		bug2.setTitle(BUG_TITLE+2);
		bug2.setDescr(BUG_DESCR+2);
		mockedBugs.add(bug1);
		mockedBugs.add(bug2);
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
		
		mockedComments = new ArrayList<>();
		BugsComments comment1 = new BugsComments();
		Bugs bug1 = new Bugs();
		bug1.setBugId(1L);
		bug1.setTitle(BUG_TITLE+1);
		bug1.setDescr(BUG_DESCR+1);
		comment1.setBug(bug1);
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
}
