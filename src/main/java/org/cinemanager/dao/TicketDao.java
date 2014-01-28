package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Ticket;

public class TicketDao extends Dao<Ticket> {

	public List<Ticket> getAllTickets() {
		EntityManager em = createContext();
		TypedQuery<Ticket> query = em.createQuery("select t from Ticket t", Ticket.class);
		List<Ticket> result = query.getResultList();
		closeContext();
		return result;
	}

	public List<Ticket> getTicketsForShowing(Long showingId) {
		EntityManager em = createContext();
		TypedQuery<Ticket> query = em.createQuery("select t from Ticket t where t.showing.id = :showingId", 
				Ticket.class);
		query.setParameter("showingId", showingId);
		List<Ticket> result = query.getResultList();
		closeContext();
		return result;
	}

}
