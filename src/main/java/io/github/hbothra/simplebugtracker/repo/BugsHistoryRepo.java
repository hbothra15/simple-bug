package io.github.hbothra.simplebugtracker.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.BugsHistory;

@Repository
public interface BugsHistoryRepo extends PagingAndSortingRepository<BugsHistory, Long> {

	List<BugsHistory> findAllByBugIdOrderByModifiedOnDesc(Long bugId);
	
	@Query("Select distinct bugId from BugsHistory bugs where createdById=:userId or modifiedById=:userId or assignedToId=:userId")
	Set<Long> findAllByUserID(Long userId);
}
