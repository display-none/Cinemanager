package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.cinemanager.dao.MarathonDao;
import org.cinemanager.entity.Marathon;
import org.cinemanager.gui.AddMarathonView;

public class MarathonController {

	private static MarathonController instance;
	private MarathonDao dao = new MarathonDao();
	
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
	
	public static MarathonController getInstance() {
		if(instance == null) {
			instance = new MarathonController();
		}
		return instance;
	} 
	public List<Marathon> getAllMarathons() { 
		List<Marathon> abc = new ArrayList<Marathon>(); 
		 
		return abc;
	} 
	public void deleteMarathon(Long id) {
		dao.remove(id, Marathon.class);
	}
}
