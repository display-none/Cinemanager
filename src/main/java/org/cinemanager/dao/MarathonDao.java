package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Marathon;

public class MarathonDao extends Dao<Marathon> {

	public List<Marathon> getAllMarathons() {
		TypedQuery<Marathon> query = getEntityManager().createQuery("select m from Marathon m", Marathon.class);
		return query.getResultList();
	}

}
