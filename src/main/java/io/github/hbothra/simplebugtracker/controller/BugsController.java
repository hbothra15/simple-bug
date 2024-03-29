package io.github.hbothra.simplebugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;
import io.github.hbothra.simplebugtracker.ro.BugRo;
import io.github.hbothra.simplebugtracker.service.BugService;

@RestController
@RequestMapping("/api/bugs")
@PreAuthorize("hasRole('VENDOR') or hasRole('SUPPORT') or hasRole('ADMIN')")
public class BugsController {
	
	@Autowired
	private BugService bugService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<BugRo> getAllBugs() {
		return bugService.findAll();
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('VENDOR') or hasRole('SUPPORT')")
	public List<BugRo> fetchAllBugsBasedOnUserId(@AuthenticationPrincipal(expression = "userId") Long userId) {
		return bugService.fetchAllBugsBasedOnUserId(userId);
	}
	
	@GetMapping("/{id}/comments")
	public List<BugCommentsRo> getBugComments(@PathVariable("id") long id) {
		return bugService.getAllComments(id);
	}
	
	@PostMapping
	public BugRo addBug(@RequestBody BugRo bug, @AuthenticationPrincipal(expression = "userId") Long userId) {
		bug.setCreatedById(userId);
		bug.setModifiedById(userId);
		return bugService.addBug(bug);
	}
	
	@PutMapping
	public BugRo updateBug(@RequestBody BugRo bug, @AuthenticationPrincipal(expression = "userId") Long userId) {
		bug.setModifiedById(userId);
		return bugService.updateBug(bug);
	}
	
}
