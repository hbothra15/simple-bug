package io.github.hbothra.simplebugtracker.repo;

import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.BugType;

@Repository
public interface BugTypeRepo extends LookupTypeRepo<BugType> {

}
