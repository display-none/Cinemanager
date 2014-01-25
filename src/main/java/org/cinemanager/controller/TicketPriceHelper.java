package org.cinemanager.controller;

import org.cinemanager.common.TicketType;

public class TicketPriceHelper {

	public int getPriceForTicketType(TicketType ticketType) {
		switch(ticketType) {
		case NORMAL:
			return 2500;
		case STUDENT:
		case SENIOR:
			return 2000;
		case CHILD:
			return 1500;
		default:
			throw new RuntimeException("some bullshit");
		}
	}
}
