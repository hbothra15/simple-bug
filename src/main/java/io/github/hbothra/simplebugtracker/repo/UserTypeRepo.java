package io.github.hbothra.simplebugtracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.hbothra.simplebugtracker.eo.UserType;

public interface UserTypeRepo extends JpaRepository<UserType, Long> {

}
