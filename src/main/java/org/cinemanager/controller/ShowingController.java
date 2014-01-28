package org.cinemanager.controller;

import java.util.Date;
import java.util.List;

import org.cinemanager.dao.ShowingDao;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.AddShowingView;


public class ShowingController {
	
	private ShowingDao dao = new ShowingDao();
	
	private static ShowingController instance;
	
	private final BookingController bookingController = BookingController.getInstance();
	
	public void createAndPersistShowing(AddShowingView addShowView) {
		Showing show = createShowing(addShowView);
		dao.persist(show);
	}
	
	public List<Showing> getAllFutureShowings() { 
		return dao.getAllShowings(new Date());
	}
	
	public List<Showing> getAllBookableShowings() {
		Date minimalDateForShowingToBeBookable = new Date(new Date().getTime() + bookingController.getNoBookingAfterDateTime());
		return dao.getAllShowings(minimalDateForShowingToBeBookable);
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
