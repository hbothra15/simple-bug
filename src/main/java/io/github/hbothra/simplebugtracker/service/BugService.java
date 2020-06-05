package io.github.hbothra.simplebugtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.github.hbothra.simplebugtracker.eo.BugType;
import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.StatusType;
import io.github.hbothra.simplebugtracker.mapper.BugsMapper;
import io.github.hbothra.simplebugtracker.mapper.CommentsMapper;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugTypeRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;
import io.github.hbothra.simplebugtracker.repo.StatusTypeRepo;
import io.github.hbothra.simplebugtracker.repo.UserRepo;
import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;
import io.github.hbothra.simplebugtracker.ro.BugRo;

@Component
@Transactional
public class BugService {

	@Autowired
	private BugsRepository bugRepo;
		
	@Autowired
	private BugsHistoryRepo hisRepo;
	
	@Autowired
	private BugCommentsRepo commRepo;
	
	@Autowired
	private BugTypeRepo bugTypeRepo;
	
	@Autowired
	private StatusTypeRepo statusRepo;
	
	@Autowired
	private UserRepo userRepo;
	
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

	public BugRo addBug(BugRo bug) {
		
		Assert.notNull(bug.getCreatedById(), "Should contain Bug creator details");
		Assert.notNull(bug.getModifiedById(), "Should contains Bug Modifier detials");
		Assert.hasText(bug.getBugType(), "Should contains Bug Type detials");
		Assert.hasText(bug.getBugStatus(), "Should contains Status Type detials");

		Optional<BugType> bugType = bugTypeRepo.findByLookupValue(bug.getBugType());
		Optional<StatusType> statusType = statusRepo.findByLookupValue(bug.getBugStatus());

		Assert.isTrue(bugType.isPresent(), "Should be a valid Bug Type");
		Assert.isTrue(statusType.isPresent(), "Should be a valid Bug Status Type");
		
		Bugs bugEo = bugMapper.destinationToSource(bug);
		if(bug.getAssignedTo() != null && bug.getAssignedTo().getUserId() != null) {
			bugEo.setAssignedTo(userRepo.findById(bug.getAssignedTo().getUserId()).orElseGet(() -> null));
		} else {
			bugEo.setAssignedTo(null);
		}
		bugEo.setBugType(bugType.get());
		bugEo.setBugStatus(statusType.get());
		
		bugEo = bugRepo.save(bugEo);
		return bugMapper.sourceToDestination(bugEo);
	}
}
