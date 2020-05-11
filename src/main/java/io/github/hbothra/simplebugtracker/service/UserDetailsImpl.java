package io.github.hbothra.simplebugtracker.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.repo.UserRepo;

public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Value("message.userNotFound")
	private String userNotFound;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		// Java email validation permitted by RFC 5322
		// used from https://howtodoinjava.com/regex/java-regex-validate-email-address
		if(Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", username)) {
			user = userRepo.findByEmail(username);
		} else {
			user = userRepo.findByContact(username);
		}
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().parallelStream().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getType()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
	}

	
}
