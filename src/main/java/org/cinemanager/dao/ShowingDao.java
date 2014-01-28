package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Showing;

public class ShowingDao extends Dao<Showing> {

	public List<Showing> getAllShowings() {
		EntityManager em = createContext();
		TypedQuery<Showing> query = em.createQuery("select s from Showing s", Showing.class);
		List<Showing> result = query.getResultList();
		closeContext();
		return result;
	}

}
