package io.github.hbothra.simplebugtracker.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.service.BugDetails;

@RestController
@RequestMapping("/api/bugs")
public class BugsController {
	
	@Autowired
	private BugDetails bugService;

	@Autowired
	private BugCommentsRepo bugComments;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Bugs> getAllBugs() {
		return bugService.findAll();
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('VENDOR') or hasRole('SUPPORT')")
	public List<Bugs> fetchAllBugsBasedOnUserId(@AuthenticationPrincipal User user) {
		return bugService.fetcchAllBugsBasedOnUserId(user.getUserId());
	}
	
	@GetMapping("/{id}/comments")
	public Optional<BugsComments> getBugComments(@PathParam("id") long id) {
		return bugComments.findById(id);
	}
	
}
