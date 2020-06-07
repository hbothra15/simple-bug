package io.github.hbothra.simplebugtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.github.hbothra.simplebugtracker.eo.BugType;
import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.StatusType;
import io.github.hbothra.simplebugtracker.mapper.BugsHistoryMapper;
import io.github.hbothra.simplebugtracker.mapper.BugsMapper;
import io.github.hbothra.simplebugtracker.mapper.CommentsMapper;
import io.github.hbothra.simplebugtracker.messages.BugMessages;
import io.github.hbothra.simplebugtracker.repo.BugCommentsRepo;
import io.github.hbothra.simplebugtracker.repo.BugTypeRepo;
import io.github.hbothra.simplebugtracker.repo.BugsHistoryRepo;
import io.github.hbothra.simplebugtracker.repo.BugsRepository;
import io.github.hbothra.simplebugtracker.repo.StatusTypeRepo;
import io.github.hbothra.simplebugtracker.repo.UserRepo;
import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;
import io.github.hbothra.simplebugtracker.ro.BugRo;
import io.github.hbothra.simplebugtracker.utils.PojoUtils;

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
	
	@Autowired
	private BugsHistoryMapper hisMapper;

	@Autowired
	private BugMessages messages;

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

	public BugRo findById(long bugId) {
		return bugMapper.sourceToDestination(bugRepo.findById(bugId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(messages.getEntityNotFound(), bugId))));
	}

	public BugRo addBug(BugRo bug) {

		Assert.notNull(bug.getCreatedById(), messages.getCreatedByNotFound());
		Assert.notNull(bug.getModifiedById(), messages.getModifiedByNotFound());
		Assert.hasText(bug.getBugType(), messages.getBugTypeNotFound());
		Assert.hasText(bug.getBugStatus(), messages.getBugStatusNotFound());

		Optional<BugType> bugType = bugTypeRepo.findByLookupValue(bug.getBugType());
		Optional<StatusType> statusType = statusRepo.findByLookupValue(bug.getBugStatus());

		Assert.isTrue(bugType.isPresent(), messages.getBugTypeNotFound());
		Assert.isTrue(statusType.isPresent(), messages.getBugStatusNotFound());

		Bugs bugEo = bugMapper.destinationToSource(bug);
		if (bug.getAssignedTo() != null && bug.getAssignedTo().getUserId() != null) {
			bugEo.setAssignedTo(userRepo.findById(bug.getAssignedTo().getUserId()).orElseGet(() -> null));
		} else {
			bugEo.setAssignedTo(null);
		}
		bugEo.setBugType(bugType.get());
		bugEo.setBugStatus(statusType.get());
		// Using saveAndFlush to make Sure we have all the fields updated before
		// Converting EO to RO, else we wont be having updated Version in resultant RO
		bugEo = bugRepo.saveAndFlush(bugEo);
		return bugMapper.sourceToDestination(bugEo);
	}

	public BugRo updateBug(BugRo bug) {
		Assert.notNull(bug.getModifiedById(), messages.getModifiedByNotFound());
		Assert.notNull(bug.getBugId(), messages.getBugIdNotFound());
		Assert.notNull(bug.getVersion(), messages.getBugVersionNotFound());

		BugRo target = findById(bug.getBugId());
		PojoUtils.updateTaregtWithValues(bug, target);

		return addBug(target);
	}

	public List<BugRo> getBugTimeLine(long bugId) {
		List<BugRo> bugTimeLine = new ArrayList<>();
		bugTimeLine.add(findById(bugId));
		bugTimeLine.addAll(hisMapper.sourceToDestination(hisRepo.findAllByBugIdOrderByModifiedOnDesc(bugId)));
		return bugTimeLine;
	}
}
