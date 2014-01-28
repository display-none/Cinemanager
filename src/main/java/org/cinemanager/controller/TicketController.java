package org.cinemanager.controller;

import java.util.List;

import org.cinemanager.dao.TicketDao;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Ticket;
import org.cinemanager.gui.AddTicketView;

import com.google.common.collect.Lists;

public class TicketController {
	
	private static TicketController instance;
	
	private TicketDao dao = new TicketDao();
	private TicketPriceHelper ticketPriceHelper = new TicketPriceHelper();
	
	private TicketController() {
		
	}
	
	public void createAndPersistTicket(AddTicketView addTicketView) {
		Ticket ticket = createTicket(addTicketView);
		dao.persist(ticket);
	}
	
	public Ticket createTicket(AddTicketView addTicketView) {
		Ticket ticket = new Ticket();
		ticket.setSeat(addTicketView.getSeat());
		ticket.setShowing(addTicketView.getShowing());
		ticket.setType(addTicketView.getTicketType());
		ticket.setPrice(ticketPriceHelper.getPriceForTicketType(addTicketView.getTicketType(), addTicketView.getSeat().isVip()));
		return ticket;
	}
	
	public void deleteTicket(Long id) {
		dao.remove(id,Ticket.class);
	}
	
	public List<Seat> getSeatsTakenForShowing(Long showingId) {
		List<Seat> takenSeats = Lists.newArrayList();
		for(Ticket ticket : dao.getTicketsForShowing(showingId)) {
			takenSeats.add(ticket.getSeat());
		}
		return takenSeats;
	}
	
	public List<Ticket> getAllTickets() {
		return dao.getAllTickets();
	}
	
	public static TicketController getInstance() {
		if(instance == null) {
			instance = new TicketController();
		}
		return instance;
	}
}
