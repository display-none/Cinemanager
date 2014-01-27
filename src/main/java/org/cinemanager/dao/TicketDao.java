package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Ticket;

public class TicketDao extends Dao<Ticket> {

	public List<Ticket> getAllTickets() {
		TypedQuery<Ticket> query = getEntityManager().createQuery("select t from Ticket t", Ticket.class);
		return query.getResultList();
	}

}
