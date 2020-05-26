package io.github.hbothra.simplebugtracker.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.hbothra.simplebugtracker.mapper.BugsMapper;
import io.github.hbothra.simplebugtracker.mapper.CommentsMapper;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;
import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;
import io.github.hbothra.simplebugtracker.ro.BugRo;

@Component
public class BugDetails {

	@Autowired
	private BugsRepository bugRepo;
	
	@Autowired
	private BugsHistoryRepo hisRepo;
	
	@Autowired
	private BugCommentsRepo commRepo;
	
	@Autowired
	private BugsMapper bugMapper;
	
	@Autowired
	private CommentsMapper commentMapper;
	
	public List<BugRo> fetchAllBugsBasedOnUserId(long userId) {
		Set<Long> bugList = bugRepo.findAllByUserID(userId);
		bugList.addAll(hisRepo.findAllByUserID(userId));
		return bugMapper.sourceToDestination(bugRepo.findAllById(bugList));
	}
	
	public List<BugRo> findAll() {
		return bugMapper.sourceToDestination(bugRepo.findAll());
	}
	
	public List<BugCommentsRo> getAllComments(Long bugId) {
		return commentMapper.sourceToDestination(commRepo.findAllByBugId(bugId));
	}
}
