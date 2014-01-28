package org.cinemanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="auditorium")
public class Auditorium implements IEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="air_conditioned")
	private boolean airConditioned = false;
	
	@Column(name="accessability_features")
	private boolean accessabilityFeatures = false;
	
	@Column(name="3d_support")
	private boolean supporting3D = false;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="auditorium_id", referencedColumnName="id")
	private List<Seat> seats;
	
	@Override
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isAirConditioned() {
		return airConditioned;
	}

	public void setAirConditioned(boolean airConditioned) {
		this.airConditioned = airConditioned;
	}

	public boolean hasAccessabilityFeatures() {
		return accessabilityFeatures;
	}

	public void setHasAccessabilityFeatures(boolean accessabilityFeatures) {
		this.accessabilityFeatures = accessabilityFeatures;
	}

	public boolean isSupporting3D() {
		return supporting3D;
	}

	public void setSupporting3D(boolean supporting3d) {
		supporting3D = supporting3d;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
}
