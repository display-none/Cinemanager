package org.cinemanager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Booking;

public class BookingDao extends Dao<Booking> {

	public List<Booking> getAllBookings() {
		EntityManager em = createContext();
		TypedQuery<Booking> query = em.createQuery("select b from Booking b", Booking.class);
		List<Booking> result = query.getResultList();
		closeContext();
		return result;
	}

	public List<Booking> getNotExpiredBookingsForShowing(Long showingId) {
		EntityManager em = createContext();
		TypedQuery<Booking> query = em.createQuery("select b from Booking b where b.showing.id = :showingId and b.expirationDate > :now", Booking.class);
		query.setParameter("showingId", showingId).setParameter("now", new Date(), TemporalType.TIME);
		List<Booking> result = query.getResultList();
		closeContext();
		return result;
	}

}
