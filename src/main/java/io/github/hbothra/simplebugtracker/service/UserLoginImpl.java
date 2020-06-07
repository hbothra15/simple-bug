package io.github.hbothra.simplebugtracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.messages.UserMessages;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

@Component
public class UserLoginImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserMessages message;

	@Override
	public UserDetails loadUserByUsername(String username) {
		SimpleUser user = Optional
				.of(userRepo.findByEmail(username).orElseThrow(
						() -> new UsernameNotFoundException(String.format(message.getEnityNotFound(), username))))
				.get();

		return new User(user.getEmail(), user.getPassword(), user.getAuthorities());
	}

}
