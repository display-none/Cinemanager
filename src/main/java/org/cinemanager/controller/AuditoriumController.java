package org.cinemanager.controller;

import java.util.List;

import org.cinemanager.dao.AuditoriumDao;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Seat;

import com.google.common.collect.Lists;

public class AuditoriumController {

	private AuditoriumDao dao = new AuditoriumDao(); 
	
	private static  AuditoriumController instance;
	
	private AuditoriumController() {
		
	}
	
	public List<Auditorium> getAuditoria() {
		return dao.getAuditoria();
	}

	public void insertAuditoriaIfDbEmpty() {
		if(dao.getAuditoria().isEmpty()) {
			Auditorium regularAuditorium = createRegularAuditorium();
			dao.persist(regularAuditorium);
			
			Auditorium irregularAuditorium = createIrregularAuditorium();
			dao.persist(irregularAuditorium);
			
			Auditorium auditoriumWithVipLounge = createAuditoriumWithVipLounge();
			dao.persist(auditoriumWithVipLounge);
			
			Auditorium oldAuditorium = createOldAuditorium();
			dao.persist(oldAuditorium);
		}
	}

	private Auditorium createOldAuditorium() {
		Auditorium auditorium = new Auditorium();
		auditorium.setAirConditioned(false);
		auditorium.setHasAccessabilityFeatures(false);
		auditorium.setName("screen 3 - small");
		auditorium.setSupporting3D(false);
		List<Seat> seats = Lists.newArrayList();
		for(int i = 0; i < 15; i++) {
			if(i != 5 && i != 6 && i != 7 && i != 8) {
				seats.add(new Seat(0, i));
			}
		}
		for(int i = 0; i < 15; i++) {
			for(int j = 1; j < 8; j++) {
				if(i != 6 && i != 7) {
					seats.add(new Seat(j, i));
				}
			}
		}
		auditorium.setSeats(seats);
		return auditorium;
	}

	private Auditorium createAuditoriumWithVipLounge() {
		Auditorium auditorium = new Auditorium();
		auditorium.setAirConditioned(true);
		auditorium.setHasAccessabilityFeatures(false);
		auditorium.setName("screen 4 - with vip lounge");
		auditorium.setSupporting3D(true);
		List<Seat> seats = Lists.newArrayList();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 8; j++) {
				seats.add(new Seat(j, i));
			}
		}
		
		for(int i = 1; i < 10; i = i+2) {
			Seat seat = new Seat(10, i);
			seat.setVip(true);
			seats.add(seat);
		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 12; j < 20; j++) {
				seats.add(new Seat(j, i));
			}
		}
		auditorium.setSeats(seats);
		return auditorium;
	}

	private Auditorium createIrregularAuditorium() {
		Auditorium auditorium = new Auditorium();
		auditorium.setAirConditioned(false);
		auditorium.setHasAccessabilityFeatures(false);
		auditorium.setName("screen 1");
		auditorium.setSupporting3D(true);
		List<Seat> seats = Lists.newArrayList();
		for(int i = 0; i < 18; i++) {
			for(int j = 0; j < 10; j = j+2) {
				seats.add(new Seat(j, i));
			}
		}
		for(int i = 1; i < 17; i++) {
			for(int j = 1; j < 10; j = j+2) {
				seats.add(new Seat(j, i));
			}
		}
		for(int i = 20; i < 24; i++) {
			for(int j = 7; j < 10; j++) {
				seats.add(new Seat(j, i));
			}
		}
		auditorium.setSeats(seats);
		return auditorium;
	}

	private Auditorium createRegularAuditorium() {
		Auditorium auditorium = new Auditorium();
		auditorium.setAirConditioned(true);
		auditorium.setHasAccessabilityFeatures(true);
		auditorium.setName("screen 2 - regular");
		auditorium.setSupporting3D(true);
		List<Seat> seats = Lists.newArrayList();
		for(int i = 0; i < 22; i++) {
			for(int j = 0; j < 13; j++) {
				if(i != 10 && i != 11 && i != 12) {
					seats.add(new Seat(j, i));
				}
			}
		}
		auditorium.setSeats(seats);
		return auditorium;
	}

	public static AuditoriumController getInstance() {
		if(instance == null) {
			instance = new AuditoriumController();
		}
		return instance;
	}
}
