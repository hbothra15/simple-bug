package io.github.hbothra.simplebugtracker.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.eo.BugsHistory;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;

@RestController
@RequestMapping("/api/bugs")
public class BugsController {

	@Autowired
	private BugsRepository bugsRepository;
	
	@Autowired
	private BugsHistoryRepo bugHistory;
	
	@Autowired
	private BugCommentsRepo bugComments;
	
	@GetMapping
	public List<Bugs> getAllBugs() {
		return bugsRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Bugs> getBug(@PathParam("id") long id) {
		return bugsRepository.findById(id);
	}
	
	@GetMapping("/{id}/comments")
	public Optional<BugsComments> getBugComments(@PathParam("id") long id) {
		return bugComments.findById(id);
	}
	
	@GetMapping("/{id}/history")
	public Optional<BugsHistory> getBugHistory(@PathParam("id") long id) {
		return bugHistory.findById(id);
	}
}
