package org.cinemanager.gui;

import org.cinemanager.entity.GroupTicket;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Movie;

public class ShowMoviesView extends View<Movie> {

	private static final long serialVersionUID = 1L;

	private ShowMoviesView(ViewManager viewManager) {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplyButtonLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCancelButtonLabel() {
		// TODO Auto-generated method stub
		return null;
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
}
