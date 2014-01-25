package org.cinemanager.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.cinemanager.common.TicketType;

@Entity
@Table(name="ticket")
public class Ticket implements IEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private TicketType type;
	
	@Column(name="price")
	private float price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="showing_id")
	private Showing showing;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seat_id")
	private Seat seat;
	
	@Override
	public Long getId() {
		return id;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Showing getShowing() {
		return showing;
	}

	public void setShowing(Showing showing) {
		this.showing = showing;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
}
