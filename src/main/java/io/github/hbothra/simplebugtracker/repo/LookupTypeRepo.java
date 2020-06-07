package io.github.hbothra.simplebugtracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.hbothra.simplebugtracker.eo.Lookup;

public interface LookupTypeRepo<T extends Lookup > extends JpaRepository<T, Long> {

	Optional<T> findByLookupValue(String lookupValue);
	
}
