package org.cinemanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.cinemanager.entity.Movie;

public class MovieDao extends Dao<Movie> {

	public List<Movie> getAllMovies() {
		TypedQuery<Movie> query = getEntityManager().createQuery("select m from Movie m", Movie.class);
		return query.getResultList();
	}

}
