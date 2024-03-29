package io.github.hbothra.simplebugtracker.eo.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=none" })
public class BeanListnerTest {

	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testGetBean() {
		Properties expected = context.getBean(Properties.class);
		Properties actual = BeanListener.getBean(Properties.class);
		assertEquals(expected, actual);
	}
}
