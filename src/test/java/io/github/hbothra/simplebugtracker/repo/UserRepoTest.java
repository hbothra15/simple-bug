package io.github.hbothra.simplebugtracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import io.github.hbothra.simplebugtracker.eo.User;
import io.github.hbothra.simplebugtracker.eo.UserRole;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepoTest {

	@Autowired
	UserRepo repo;
	
	@Test
	public void testFindByUserName() {
		com.github.hbothra.user.entity.User user = repo.findByUserName("admin@simpleBug.com").get();
		assertTrue(user instanceof User, "User should be instance of EO User");
		assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
	}
	
	@Test
	public void testFindByEmail() {
		User user = repo.findByEmail("admin@simpleBug.com").get();
		assertEquals(user.getName(), "ADMIN");
		List<UserRole> userRole = new ArrayList<UserRole>(user.getRoles());
		assertEquals(userRole.get(0).getType(), "ADMIN");
	}
	
	@Test
	public void testFindByContact() {
		User user = repo.findByContact("7700000000").get();
		assertEquals(user.getName(), "ADMIN");
	}
}
