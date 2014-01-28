package org.cinemanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinemanager.common.ShowingVersion;

@Entity
@Table(name="showing")
public class Showing implements IEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Movie movie;
	
	@Temporal(TemporalType.TIME)
	@Column(name="date", nullable=false)
	private Date date;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Auditorium auditorium;
	
	@Enumerated(EnumType.STRING)
	@Column(name="version", nullable=false)
	private ShowingVersion version;
	
	@ManyToOne
	@JoinColumn(name="supervising_employee_id", nullable=false)
	private Employee supervisingEmployee;
	
	@Override
	public Long getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public ShowingVersion getVersion() {
		return version;
	}

	public void setVersion(ShowingVersion version) {
		this.version = version;
	}

	public Employee getSupervisingEmployee() {
		return supervisingEmployee;
	}

	public void setSupervisingEmployee(Employee supervisingEmployee) {
		this.supervisingEmployee = supervisingEmployee;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
