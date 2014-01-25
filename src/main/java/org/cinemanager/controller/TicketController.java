package org.cinemanager.controller;

import org.cinemanager.dao.TicketDao;
import org.cinemanager.entity.Ticket;
import org.cinemanager.gui.AddTicketView;

public class TicketController {
private static TicketController instance;
	
	private TicketDao dao = new TicketDao();
	
	public void createAndPersistMovie(AddTicketView addTicketView) {
		Ticket ticket = createMovie(addTicketView);
		dao.persist(ticket);
	}
	
	public Ticket createMovie(AddTicketView addTicketView) {
		Ticket ticket = new Ticket();
		ticket.setSeat(addTicketView.getseatid());
		ticket.setShowing(addTicketView.getshowid());
		ticket.setType(addTicketView.gettype());
		ticket.setPrice(addTicketView.getprice());
	//	ticket.setgroupticket(addTicketView.getgroupticket());    /** Piter : dobrze ta linia ? **/
		return ticket;
	}
	
	public static TicketController getInstance() {
		if(instance == null) {
			instance = new TicketController();
		}
		return instance;
	}
}
