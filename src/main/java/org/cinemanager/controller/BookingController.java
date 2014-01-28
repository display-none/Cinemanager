package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.cinemanager.dao.BookingDao;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Ticket;
import org.cinemanager.gui.AddBookingView;

import com.google.common.collect.Lists;


public class BookingController {
	
	private static final long HALF_HOUR_IN_MILISECONDS = 30*60*1000;

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
		booking.setShowing(addBookingView.getShowing()); 
		booking.setSeat(addBookingView.getSeat()); 
		booking.setExpirationDate(new Date(booking.getShowing().getDate().getTime() - HALF_HOUR_IN_MILISECONDS));
		return booking;
	}
	
	public List<Seat> getBookedSeatsForShowing(Long showingId) {
		List<Seat> takenSeats = Lists.newArrayList();
		for(Booking booking : dao.getNotExpiredBookingsForShowing(showingId)) {
			takenSeats.add(booking.getSeat());
		}
		return takenSeats;
	}
	
	public long getNoBookingAfterDateTime() {
		return HALF_HOUR_IN_MILISECONDS;
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
