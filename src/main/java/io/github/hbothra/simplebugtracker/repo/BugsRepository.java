package io.github.hbothra.simplebugtracker.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.Bugs;

@Repository
public interface BugsRepository extends JpaRepository<Bugs, Long> {

	@Query("Select distinct bugId from Bugs bugs where createdById=:userId or modifiedById=:userId or assignedToId=:userId")
	Set<Long> findAllByUserID(Long userId);

}
