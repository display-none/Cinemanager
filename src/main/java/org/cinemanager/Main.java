package org.cinemanager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.entity.GroupTicket;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Ticket;

import com.google.common.collect.Lists;

public class Main {

	
	public static void main(String[] args) {
		System.out.print("hello");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("openjpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Movie movie = new Movie();
		movie.setTitle("sth");
		movie.setGenre(MovieGenre.ACTION);
		movie.setReleaseDate(new Date());
		movie.setRuntime(123);
		movie.setVersion(MovieVersion.BOTH);
		
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		GroupTicket gt = new GroupTicket();
		gt.setTickets(Lists.newArrayList(t1, t2, t3));
		
		em.persist(gt);
		
		em.persist(movie);
		
		em.flush();
		em.clear();
		Query query = em.createQuery("select m from Movie m");
		List<Movie> results = query.getResultList();
		
		System.out.println(results.get(0).getTitle());
		
		em.getTransaction().commit();
		em.close();
	}
}
