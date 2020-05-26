package io.github.hbothra.simplebugtracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Value("${message.userNotFound:'Unable to find any user with %s'")
	private String userNotFound;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SimpleUser user = null;
		user = Optional
				.of(userRepo.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException(String.format(userNotFound, username))))
				.get();
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
	}

	
}
