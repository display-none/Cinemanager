package org.cinemanager.entity;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {
	
	private EntityManager em;

	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("openjpa");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
	}
	
	@Test
	public void test_shouldPersistMovie() throws Exception {
		//given
		Movie movie = new Movie();
		movie.setTitle("sth");
		movie.setGenre(MovieGenre.ACTION);
		movie.setReleaseDate(new Date());
		movie.setRuntime(123);
		movie.setVersion(MovieVersion.BOTH);
		
		//when
		em.persist(movie);
		
		//then
		em.flush();
		em.clear();
		Query query = em.createQuery("select m from Movie m");
		List<Movie> results = query.getResultList();
		assertFalse(results.isEmpty());
	}
}
