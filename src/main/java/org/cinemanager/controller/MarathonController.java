package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.cinemanager.dao.MarathonDao;
import org.cinemanager.entity.Marathon;
import org.cinemanager.gui.AddMarathonView;

public class MarathonController {

	private MarathonDao dao = new MarathonDao();
	
	private static MarathonController instance;
	
	public void createAndPersistMarathon(AddMarathonView addMarathonView) {
		Marathon marathon = createMarathon(addMarathonView);
		dao.persist(marathon);
	}
	
	public Marathon createMarathon(AddMarathonView addMarathonView) {
		Marathon marathon = new Marathon();
		 
		marathon.setName(addMarathonView.getName()); 
		marathon.setSupervisingEmployee(addMarathonView.getEmployee()); 
		
		return marathon;
	}
	
	public List<Marathon> getAllMarathons() { 
		return dao.getAllMarathons();
	} 
	
	public void deleteMarathon(Long id) {
		dao.remove(id, Marathon.class);
	}
	
	public static MarathonController getInstance() {
		if(instance == null) {
			instance = new MarathonController();
		}
		return instance;
	} 
}
