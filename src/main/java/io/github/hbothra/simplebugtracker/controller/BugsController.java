package io.github.hbothra.simplebugtracker.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;

@RestController
@RequestMapping("/api/bugs")
public class BugsController {

	@Autowired
	private BugsRepository bugsRepository;
	
	@GetMapping
	public List<Bugs> getAllBugs() {
		return bugsRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Bugs> getBug(@PathParam("id") long id) {
		return bugsRepository.findById(id);
	}
}
