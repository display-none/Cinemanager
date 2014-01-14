package org.cinemanager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.common.ShowingVersion;
import org.cinemanager.common.TicketType;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.GroupTicket;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;
import org.cinemanager.entity.Ticket;

import com.google.common.collect.Lists;

public class Main {

	
	public static void main(String[] args) {
		System.out.print("hello123");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("openjpa");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Movie movie = new Movie();
		movie.setTitle("sth");
		movie.setGenre(MovieGenre.ACTION);
		movie.setReleaseDate(new Date());
		movie.setRuntime(123);
		movie.setVersion(MovieVersion.BOTH);
		em.persist(movie);
		
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		GroupTicket gt = new GroupTicket();
		gt.setTickets(Lists.newArrayList(t1, t2, t3));
		em.persist(gt);
		
		Auditorium auditorium = new Auditorium();
		em.persist(auditorium);
		
		Employee employee = new Employee();
		employee.setFirstName("dupa");
		employee.setLastName("maryna");
		employee.setPosition(EmployeePosition.MANAGER);
		em.persist(employee);
		
		Showing showing = new Showing();
		showing.setAuditorium(auditorium);
		showing.setDate(new Date());
		showing.setSupervisingEmployee(employee);
		showing.setVersion(ShowingVersion.VERSION_2D);
		showing.setMovie(movie);
		em.persist(showing);
		
		Seat seat = new Seat();
		seat.setAuditorium(auditorium);
		seat.setRow(0);
		seat.setNumber(0);
		em.persist(seat);
		
		Booking booking = new Booking();
		booking.setExpirationDate(new Date());
		booking.setShowing(showing);
		booking.setSeat(seat);
		em.persist(booking);
		
		Ticket ticket = new Ticket();
		ticket.setPrice(3.5f);
		ticket.setSeat(seat);
		ticket.setShowing(showing);
		ticket.setType(TicketType.NORMAL);
		em.persist(ticket);
		
		em.flush();
		em.clear();
		Query query = em.createQuery("select m from Movie m");
		List<Movie> results = query.getResultList();
		
		System.out.println(results.get(0).getTitle());
		
		tx.commit();
		em.close();
	}
}
