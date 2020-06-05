package io.github.hbothra.simplebugtracker.eo.listener;

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
		if(null != target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
	}
	
	@PreUpdate
	private void beforeUpdate(Bugs target) {
		if(null == target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
		saveDataToHistory(target);
	}

	@Transactional(TxType.MANDATORY)
	private void saveDataToHistory(Bugs target) {
		EntityManager em = BeanListener.getBean(EntityManager.class);
		em.persist(new BugsHistory(target, target.getBugId()));
	}
}
