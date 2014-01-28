package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Auditorium;

public class AuditoriumDao extends Dao<Auditorium> {

	public List<Auditorium> getAuditoria() {
		EntityManager em = createContext();
		TypedQuery<Auditorium> query = em.createQuery("select a from Auditorium a", Auditorium.class);
		List<Auditorium> result = query.getResultList();
		closeContext();
		return result;
	}
}
