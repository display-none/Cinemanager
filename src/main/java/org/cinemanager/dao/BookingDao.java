package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Booking;

public class BookingDao extends Dao<Booking> {

	public List<Booking> getAllBookings() {
		TypedQuery<Booking> query = getEntityManager().createQuery("select b from Booking b", Booking.class);
		return query.getResultList();
	}

}
