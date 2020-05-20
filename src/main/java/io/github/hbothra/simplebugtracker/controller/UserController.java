package io.github.hbothra.simplebugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping
	public User addUser(@RequestBody User user, @AuthenticationPrincipal User loggedInUser) {
		Assert.isTrue(user.getPassword().equals(user.getPasswordConfirm()), "Password and Password Confirm is not same");
		user.setCreatedBy(loggedInUser);
		user.setModifiedBy(loggedInUser);
		return userRepo.save(user);
	}

	@GetMapping
	public List<User> getAllUser() {
		return userRepo.findAll(Sort.by(Direction.ASC, "name"));
	}
	
	@GetMapping("/current")
	public void currentUser(@AuthenticationPrincipal User user) {
		System.err.println(user.getAuthorities());
	}
}
