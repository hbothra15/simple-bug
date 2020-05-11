package io.github.hbothra.simplebugtracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String emailId);
	
	User findByContact(String contact);
}
