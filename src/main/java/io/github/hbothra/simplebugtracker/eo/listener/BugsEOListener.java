package io.github.hbothra.simplebugtracker.eo.listener;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsHistory;

public class BugsEOListener {

	@PrePersist
	private void beforePersist(Bugs target) {
		if(null == target.getProject()) {
			target.setProject(0L);
		}
		target.setCreatedOn(LocalDateTime.now());
		target.setModifiedOn(LocalDateTime.now());
	}
	
	@PreUpdate
	private void beforeUpdate(Bugs target) {
		saveDataToHistory(target);
		target.setModifiedOn(LocalDateTime.now());
	}

	@Transactional(TxType.MANDATORY)
	private void saveDataToHistory(Bugs target) {
		EntityManager em = BeanListener.getBean(EntityManager.class);
		em.persist(new BugsHistory(target, target.getBugId()));
	}
}
