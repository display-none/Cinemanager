package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Movie;

public class MovieDao extends Dao<Movie> {

	public List<Movie> getAllMovies() {
		EntityManager em = createContext();
		TypedQuery<Movie> query = em.createQuery("select m from Movie m", Movie.class);
		List<Movie> result = query.getResultList();
		closeContext();
		return result;
	}

}
