package io.github.hbothra.simplebugtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.BugsHistory;

@Repository
public interface BugsHistoryRepo extends JpaRepository<BugsHistory, Long> {

	List<BugsHistory> findAllByBugId(Long bugId);
}
