package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Marathon;

public class MarathonDao extends Dao<Marathon> {

	public List<Marathon> getAllMarathons() {
		EntityManager em = createContext();
		TypedQuery<Marathon> query = em.createQuery("select m from Marathon m", Marathon.class);
		List<Marathon> result = query.getResultList();
		closeContext();
		return result;
	}

}
