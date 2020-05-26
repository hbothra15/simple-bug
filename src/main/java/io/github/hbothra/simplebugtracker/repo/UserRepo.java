package io.github.hbothra.simplebugtracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.hbothra.user.crud.UserCrudService;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;

@Repository
public interface UserRepo extends JpaRepository<SimpleUser, Long>, UserCrudService {

	@EntityGraph(value = "User.role", type = EntityGraphType.LOAD)
	Optional<SimpleUser> findByEmail(String emailId);
	
	Optional<SimpleUser> findByContact(String contact);
	
	@Override
	@EntityGraph(value = "User.role", type = EntityGraphType.LOAD)
	default Optional<com.github.hbothra.user.entity.User> findByUserName(String userName) {
		return Optional.of(findByEmail(userName).orElseThrow());
	}
}
