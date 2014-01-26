package org.cinemanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.dao.MovieDao;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.Movie;
import org.cinemanager.gui.AddMovieView;

public class MovieController {

	private static MovieController instance;
	
	private MovieDao dao = new MovieDao();
	
	public void createAndPersistMovie(AddMovieView addMovieView) {
		Movie movie = createMovie(addMovieView);
		dao.persist(movie);
	} 
	public List<Movie> getAllMovies() {  
		String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);  
		String date = "2000-02-02";
		
		List<Movie> abc = new ArrayList<Movie>(); 
			Movie e1 = new Movie(); 
			Movie e2 = new Movie(); 
			Movie e3 = new Movie(); 
			 
			e1.setGenre(MovieGenre.ACTION); 
			e1.setMinimalAge(18); 
			try {
				e1.setReleaseDate(dateParser.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			e1.setVersion(MovieVersion.VERSION_3D);  
			e1.setRuntime(200);
			e1.setTitle("G³ebokie gardlo"); 
			 
			e2.setGenre(MovieGenre.ACTION); 
			e2.setMinimalAge(18); 
			try {
				e2.setReleaseDate(dateParser.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			e2.setVersion(MovieVersion.VERSION_3D);  
			e2.setRuntime(200);
			e2.setTitle("G³ebokie gardlo2"); 
			 
			e3.setGenre(MovieGenre.ACTION); 
			e3.setMinimalAge(18); 
			try {
				e3.setReleaseDate(dateParser.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			e3.setVersion(MovieVersion.VERSION_3D);  
			e3.setRuntime(200);
			e3.setTitle("G³ebokie gardlo3");
			 
			abc.add(e1); 
			abc.add(e2); 
			abc.add(e3); 
			 
			return abc;
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
	public void deleteMovie(Long id) { 
		System.out.println("ID : "+id);
		dao.remove(id, Movie.class);
	}
	
	public static MovieController getInstance() {
		if(instance == null) {
			instance = new MovieController();
		}
		return instance;
	}
}
