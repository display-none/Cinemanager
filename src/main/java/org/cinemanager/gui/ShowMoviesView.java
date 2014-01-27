package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.controller.MovieController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Movie;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowMoviesView extends View<Movie> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private static JList<Movie> movieList;
	private static final MovieController controller = MovieController.getInstance();
	private ShowMoviesView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Movie> movies = controller.getAllMovies();
		
		movieList = new EntityList<Movie>(movies, new MovieFormatter(), new ActionListenerCreator()); 
		this.add(movieList);
	}
	
	@Override
	public boolean hasAnyChanges() {
		return false;
	}

	@Override
	public boolean areInputsValid() {
		return !movieList.isSelectionEmpty();
	}

	@Override
	public void doApplyAction() {

	}

	@Override
	public Movie doGetResultAction() {
		return movieList.getSelectedValue();
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL;
	}
	
	@Override
	public String getCancelButtonLabel() {
		return CANCEL_BUTTON_LABEL;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public static ViewCreator<ShowMoviesView> getCreator() {
		return new ShowMoviesViewCreator();
	}
	
	private static class ShowMoviesViewCreator implements ViewCreator<ShowMoviesView> {

		@Override
		public ShowMoviesView createView(ViewManager viewManager) {
			return new ShowMoviesView(viewManager);
		}
	} 
	
	private static class MovieFormatter implements EntityFormatter<Movie> {

		private static final String DATE_FORMAT = "MMM dd, yyyy";  
		private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
		
		@Override
		public String getLabelText(Movie entity) {
			return entity.getTitle() + " " + 
						parseVersion(entity.getVersion()) + 
						" (" + parseGenre(entity.getGenre()) + ")" + 
						" released " + parseDate(entity.getReleaseDate()) + " " + 
						" (" + entity.getRuntime() + " minutes" +
								parseMinimalAge(entity.getMinimalAge()) + 
						")" ;
		}

		private String parseMinimalAge(int minimalAge) {
			return minimalAge != 0 
						? (", min. age " + minimalAge)
						: ("");
		}
		
		private String parseDate(Date date) {
			return dateParser.format(date);
		}
		
		private String parseGenre(MovieGenre genre) {
			String genreString = genre.toString().toLowerCase();
			return genreString.replaceAll("_", " ");
		}

		private String parseVersion(MovieVersion version) {
			switch(version) {
			case VERSION_2D:
				return "2D";
			case VERSION_3D:
				return "3D";
			case BOTH:
				return "2D and 3D";
			default:
				return null;
			}
		}
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id, ActionListener deleteSuccessfulCallback) {
			return new DeleteActionListener(id, deleteSuccessfulCallback);
		}
	}
	
	private static class DeleteActionListener implements ActionListener {
		
		private final Long id;
		private final ActionListener callback;
		
		public DeleteActionListener(Long id, ActionListener callback) {
			this.id = id;
			this.callback = callback;
		}

		@Override
		public void actionPerformed(ActionEvent e) { 
			if (isUserSureToDeleteEntry() ) { 
				controller.deleteMovie(id);
				callback.actionPerformed(null); 
			} 
		} 

		private boolean isUserSureToDeleteEntry() {
			return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(movieList, "Are you sure you want to delete this entry?", null, JOptionPane.YES_NO_OPTION);
		}
	}
}
