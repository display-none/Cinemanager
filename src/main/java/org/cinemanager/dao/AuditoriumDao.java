package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Auditorium;

public class AuditoriumDao extends Dao<Auditorium> {

	public List<Auditorium> getAuditoria() {
		TypedQuery<Auditorium> query = getEntityManager().createQuery("select a from Auditorium a", Auditorium.class);
		return query.getResultList();
	}
}
