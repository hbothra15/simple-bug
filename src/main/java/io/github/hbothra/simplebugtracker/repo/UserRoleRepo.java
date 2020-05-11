package io.github.hbothra.simplebugtracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.hbothra.simplebugtracker.eo.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

}
