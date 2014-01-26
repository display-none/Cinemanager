package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import org.cinemanager.controller.MovieController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Movie;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowMoviesView extends View<Movie> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private JList<Movie> movieList;
	private static final MovieController controller = MovieController.getInstance();
	
	
	private ShowMoviesView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Movie> movies = controller.getAllMovies();
		
		movieList = new EntityList<Movie>(movies, new MovieFormatter(), new ActionListenerCreator()); 
		this.add(movieList);
	}
	
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doApplyAction() {
		// TODO Auto-generated method stub

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

		@Override
		public String getLabelText(Movie entity) {
			return entity.getTitle()+ "   " + entity.getReleaseDate().toString() + "   " + entity.getGenre().toString() + "   " + entity.getRuntime() + "   " + entity.getMinimalAge() + "   " + entity.getVersion().toString() ;
		}
		
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id) {
			return new DeleteActionListener(id);
		}
		
	}
	private static class DeleteActionListener implements ActionListener {
		
		private Long id;
		
		public DeleteActionListener(Long id) { 
			System.out.println("ID# : "+id);
			this.id = id;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.deleteMovie(id);
		}
	}
}
