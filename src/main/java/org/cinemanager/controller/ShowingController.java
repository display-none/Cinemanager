package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.cinemanager.dao.ShowingDao;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.AddShowingView;


public class ShowingController {
	
	private ShowingDao dao = new ShowingDao();
	
	private static ShowingController instance;
	
	public void createAndPersistShowing(AddShowingView addShowView) {
		Showing show = createShowing(addShowView);
		dao.persist(show);
	}
	
	public List<Showing> getAllShowings() { 
		return dao.getAllShowings();
	}
	
	public Showing createShowing(AddShowingView addShowView) {
		Showing showing = new Showing();
		showing.setDate(addShowView.getDate()); 
		showing.setMovie(addShowView.getMovie()); 
		showing.setVersion(addShowView.getVersion()); 
		showing.setAuditorium(addShowView.getAuditorium()); 
		showing.setSupervisingEmployee(addShowView.getEmployee()); 
		return showing;
	}
	
	public void deleteShowing(Long id) {
		dao.remove(id, Showing.class);
	}
	
	public static ShowingController getInstance() {
		if(instance == null) {
			instance = new ShowingController();
		}
		return instance;
	} 
}
