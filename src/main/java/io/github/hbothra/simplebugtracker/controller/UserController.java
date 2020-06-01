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

import io.github.hbothra.simplebugtracker.ro.SimpleUserRo;
import io.github.hbothra.simplebugtracker.service.UserService;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public SimpleUserRo addUser(@RequestBody SimpleUserRo user, @AuthenticationPrincipal SimpleUserRo loggedInUser) {
		Assert.isTrue(user.getPassword().equals(user.getPasswordConfirm()), "Password and Password Confirm is not same");
		user.setCreatedBy(loggedInUser);
		user.setModifiedBy(loggedInUser);
		return userService.addUser(user);
	}

	@GetMapping
	public List<SimpleUserRo> getAllUser() {
		return userService.findAllUsersSorted(Sort.by(Direction.ASC, "name"));
	}

}
