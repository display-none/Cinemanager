package org.cinemanager.controller;

import org.cinemanager.dao.ShowingDao;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.AddShowingView;


public class ShowingController { 
	private static ShowingController instance;
	private ShowingDao dao = new ShowingDao();
	
	public void createAndPersistShowing(AddShowingView addShowView) {
		Showing show = createShow(addShowView);
		dao.persist(show);
	}
	
	public Showing createShow(AddShowingView addShowView) {
		Showing show = new Showing();
		show.setDate(addShowView.getDate()); 
		show.setMovie(addShowView.getMovie()); 
		show.setVersion(addShowView.getVersion()); 
		show.setAuditorium(addShowView.getAuditorium()); 
		show.setMarathon(addShowView.getMarathon()); 
		show.setSupervisingEmployee(addShowView.getEmployee()); 
		return show ;
	}
	public static ShowingController getInstance() {
		if(instance == null) {
			instance = new ShowingController();  						/** NIE ROZUMIEM O CO MU CHODZI **/ 
		}
		return instance;
	}
}
