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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="marathon")
public class Marathon implements IEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@JoinColumn(name="supervising_employee_id", nullable=false)
	private Employee supervisingEmployee;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="marathon_id", referencedColumnName="id", nullable=true)
	private List<Showing> showings;

	
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

	public Employee getSupervisingEmployee() {
		return supervisingEmployee;
	}

	public void setSupervisingEmployee(Employee supervisingEmployee) {
		this.supervisingEmployee = supervisingEmployee;
	}

	public List<Showing> getShowings() {
		return showings;
	}

	public void setShowings(List<Showing> showings) {
		this.showings = showings;
	}
}
