package io.github.hbothra.simplebugtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.hbothra.auth.UserAuthenticationService;

@RequestMapping("/public")
public class UserController {

	@Autowired
	private UserAuthenticationService userAuthService;
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("pass")String password) {
		return userAuthService.login(userName, password).orElseThrow(() -> new RuntimeException("invalid username / password"));
	}
}
