package io.github.hbothra.simplebugtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.BugsComments;

@Repository
public interface BugCommentsRepo extends JpaRepository<BugsComments, Long> {

	List<BugsComments> findAllByBugId(Long bugId);
}
