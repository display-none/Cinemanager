package org.cinemanager.controller;

import org.cinemanager.dao.BookingDao;
import org.cinemanager.entity.Booking;
import org.cinemanager.gui.AddBookingView;


public class BookingController {
	private BookingDao dao = new BookingDao(); 
	private static  BookingController instance;	
	public void createAndPersistBooking(AddBookingView addBookingView) {
		Booking booking= createBooking(addBookingView);
		dao.persist(booking);
	}
	
	public Booking createBooking(AddBookingView addBookingView) {
		Booking booking = new Booking();
		booking.setShowing(addBookingView.getShow()); 
		booking.setSeat(addBookingView.getSeat()); 
		booking.setExpirationDate(addBookingView.getDate());
		return booking;
	}
	
	public static  BookingController getInstance() {
		if(instance == null) {
			instance = new  BookingController();
		}
		return instance;
	}
}
