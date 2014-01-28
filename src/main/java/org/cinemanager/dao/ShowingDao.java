package org.cinemanager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Showing;

public class ShowingDao extends Dao<Showing> {

	public List<Showing> getAllShowings(Date startingAfterDate) {
		EntityManager em = createContext();
		TypedQuery<Showing> query = em.createQuery("select s from Showing s where s.date > :startingAfterDate", Showing.class);
		query.setParameter("startingAfterDate", startingAfterDate, TemporalType.TIMESTAMP);
		List<Showing> result = query.getResultList();
		closeContext();
		return result;
	}

}
