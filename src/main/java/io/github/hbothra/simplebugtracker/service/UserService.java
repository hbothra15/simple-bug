package io.github.hbothra.simplebugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.mapper.SimpleUserMapper;
import io.github.hbothra.simplebugtracker.repo.UserRepo;
import io.github.hbothra.simplebugtracker.ro.SimpleUserRo;

@Component
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SimpleUserMapper userMapper;
	
	public SimpleUserRo addUser(SimpleUserRo user) {
		SimpleUser dbUser = userRepo.save(userMapper.destinationToSource(user));
		return userMapper.sourceToDestination(dbUser);
	}
	
	public List<SimpleUserRo> findAllUsersSorted(Sort sort) {
		List<SimpleUser> dbUsers = userRepo.findAll(sort);
		return userMapper.sourceToDestination(dbUsers);
	}
}
