package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Showing;

public class ShowingDao extends Dao<Showing> {

	public List<Showing> getAllShowings() {
		TypedQuery<Showing> query = getEntityManager().createQuery("select s from Showing s", Showing.class);
		return query.getResultList();
	}

}
