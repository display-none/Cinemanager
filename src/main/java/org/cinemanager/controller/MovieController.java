package org.cinemanager.controller;

import org.cinemanager.dao.MovieDao;
import org.cinemanager.entity.Movie;
import org.cinemanager.gui.AddMovieView;

public class MovieController {

	private static MovieController instance;
	
	private MovieDao dao = new MovieDao();
	
	public void createAndPersistMovie(AddMovieView addMovieView) {
		Movie movie = createMovie(addMovieView);
		dao.persist(movie);
	}
	
	public Movie createMovie(AddMovieView addMovieView) {
		Movie movie = new Movie();
		movie.setGenre(addMovieView.getGenre());
		movie.setMinimalAge(addMovieView.getMinimalAge());
		movie.setReleaseDate(addMovieView.getReleaseDate());
		movie.setRuntime(addMovieView.getRuntime());
		movie.setTitle(addMovieView.getTitle());
		movie.setVersion(addMovieView.getVersion());
		return movie;
	}
	
	public static MovieController getInstance() {
		if(instance == null) {
			instance = new MovieController();
		}
		return instance;
	}
}
