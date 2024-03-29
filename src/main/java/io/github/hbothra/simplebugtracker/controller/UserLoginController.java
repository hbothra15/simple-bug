package io.github.hbothra.simplebugtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.hbothra.auth.UserAuthenticationService;

@RestController
public class UserLoginController {

	@Autowired
	private UserAuthenticationService userAuthService;
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("pass")String password) {
		return userAuthService.login(userName, password).orElseThrow(() -> new RuntimeException("invalid username / password"));
	}
}
