package io.github.hbothra.simplebugtracker.eo.listener;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.eo.BugsHistory;
import io.github.hbothra.simplebugtracker.utils.PojoUtils;

public class BugsEOListener {
	
	@PostLoad
	private void afterLoad(Bugs bug) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		bug.setBeforeChange((Bugs)PojoUtils.deepClone(bug));
	}

	@PrePersist
	private void beforePersist(Bugs target) {
		if (null == target.getProject()) {
			target.setProject(0L);
		}
		if (null == target.getAssignedToId() && null != target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
	}

	@PreUpdate
	private void beforeUpdate(Bugs target) {
		if (null == target.getAssignedToId() && null != target.getAssignedTo()) {
			target.setAssignedToId(target.getAssignedTo().getUserId());
		}
		saveDataToHistory(target);
	}

	@Transactional(TxType.MANDATORY)
	private void saveDataToHistory(Bugs target) {
		EntityManager em = BeanListener.getBean(EntityManager.class);
		em.persist(new BugsHistory(target.getBeforeChange(), target.getBugId()));
	}
}
