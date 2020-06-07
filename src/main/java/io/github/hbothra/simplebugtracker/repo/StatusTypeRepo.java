package io.github.hbothra.simplebugtracker.repo;

import org.springframework.stereotype.Repository;

import io.github.hbothra.simplebugtracker.eo.StatusType;

@Repository
public interface StatusTypeRepo extends LookupTypeRepo<StatusType> {

}
