package io.github.hbothra.simplebugtracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.hbothra.user.crud.UserCrudService;

import io.github.hbothra.simplebugtracker.eo.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, UserCrudService {

	@EntityGraph(value = "User.role", type = EntityGraphType.LOAD)
	Optional<User> findByEmail(String emailId);
	
	Optional<User> findByContact(String contact);
	
	@Override
	@EntityGraph(value = "User.role", type = EntityGraphType.LOAD)
	default Optional<com.github.hbothra.user.entity.User> findByUserName(String userName) {
		return Optional.of(findByEmail(userName).orElseThrow());
	}
}
