package org.cinemanager.entity;

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
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private TicketType type;
	
	@Column(name="price")
	private float price;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="showingId")
//	private Showing showing;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="seatId")
//	private Seat seat;
	
	
}
