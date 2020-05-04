package io.github.hbothra.simplebugtracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.Bugs;

@Repository
public interface BugsRepository extends JpaRepository<Bugs, Long> {

}
