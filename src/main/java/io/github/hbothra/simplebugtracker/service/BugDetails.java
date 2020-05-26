package io.github.hbothra.simplebugtracker.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;

@Component
public class BugDetails {

	@Autowired
	private BugsRepository bugRepo;
	
	@Autowired
	private BugsHistoryRepo hisRepo;
	
	@Autowired
	private BugCommentsRepo commRepo;
		
	public List<Bugs> fetchAllBugsBasedOnUserId(long userId) {
		Set<Long> bugList = bugRepo.findAllByUserID(userId);
		bugList.addAll(hisRepo.findAllByUserID(userId));
		return bugRepo.findAllById(bugList);
	}
	
	public List<Bugs> findAll() {
		return bugRepo.findAll();
	}
	
	public List<BugsComments> getAllComments(Long bugId) {
		return commRepo.findAllByBugId(bugId);
	}
}
