package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Booking> getAllBookings() {
		return dao.getAllBookings();
	}
	
	public Booking createBooking(AddBookingView addBookingView) {
		Booking booking = new Booking();
		booking.setShowing(addBookingView.getShow()); 
		booking.setSeat(addBookingView.getSeat()); 
		booking.setExpirationDate(addBookingView.getDate());
		return booking;
	}
	
	public void deleteBooking(Long id) {
		dao.remove(id, Booking.class);
	}
	
	public static  BookingController getInstance() {
		if(instance == null) {
			instance = new  BookingController();
		}
		return instance;
	} 
}
