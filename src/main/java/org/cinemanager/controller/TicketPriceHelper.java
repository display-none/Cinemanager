package org.cinemanager.controller;

import org.cinemanager.common.TicketType;

public class TicketPriceHelper {

	public int getPriceForTicketType(TicketType ticketType, boolean vip) {
		int price;
		switch(ticketType) {
			case NORMAL:
				price = 2500;
				break;
			case STUDENT:
			case SENIOR:
				price = 2000;
				break;
			case CHILD:
				price = 1500;
				break;
			default:
				throw new RuntimeException("some bullshit");
		}
		if(vip) price = price * 2;
		return price;
	}
}
