package io.github.hbothra.simplebugtracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.hbothra.simplebugtracker.eo.UserType;

@SpringBootTest
@ActiveProfiles("test")
public class UserTypeRepositoryTest {

	@Autowired
	private UserTypeRepo userTypeRepo;
	
	@Test
	public void verifyAllUsers() {
		List<UserType> userTypes = userTypeRepo.findAll();
		userTypes.forEach(userType -> {
			System.out.println(userType);
		});
		assertEquals(3, userTypes.size());
	}
}
