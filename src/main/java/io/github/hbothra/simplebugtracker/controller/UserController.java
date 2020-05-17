package io.github.hbothra.simplebugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.eo.UserRole;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@RestController
@RequestMapping("/api/users")
@Secured({ "ADMIN" })
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping
	public User login(@RequestAttribute("name") String name, @RequestAttribute("address1") String address1,
			@RequestAttribute("address2") String address2, @RequestAttribute("address3") String address3,
			@RequestAttribute("email") String email, @RequestAttribute("phn") String contact,
			@RequestAttribute("role") UserRole role, @RequestAttribute("password") String password,
			@RequestAttribute("passwordConfirm") String passwordConfirm) {

		Assert.isTrue(password.equals(passwordConfirm), "Password and Password Confirm is not same");

		User user = new User();
		user.setAddressLine1(address1);
		user.setAddressLine2(address2);
		user.setAddressLine3(address3);
		user.setContact(contact);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.addRole(role);
		return userRepo.save(user);
	}

	@GetMapping
	public List<User> getAllUser() {
		return userRepo.findAll(Sort.by(Direction.ASC, "name"));
	}
}
