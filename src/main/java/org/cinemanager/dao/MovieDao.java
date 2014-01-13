package org.cinemanager.dao;

import java.util.Collection;

import org.cinemanager.entity.Movie;

public class MovieDao extends Dao {

	public void persist(Movie movie) {
		createContext();
		if(movie.getId() == null) {
			em.persist(movie);
		} else {
			em.merge(movie);
		}
		closeContext();
	}
	
	public void persist(Collection<Movie> movies) {
		createContext();
		for(Movie movie : movies) {
			if(movie.getId() == null) {
				em.persist(movie);
			} else {
				em.merge(movie);
			}
		}
		closeContext();
	}

	public void remove(Long id) {
		createContext();
		Movie movie = em.getReference(Movie.class, id);
		em.remove(movie);
		closeContext();
	}
	
}
