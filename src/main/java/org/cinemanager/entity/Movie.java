package org.cinemanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;

@Entity
@Table(name="movie")
public class Movie implements IEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="release_date", nullable=false)
	private Date releaseDate;
	
	@Enumerated(value=EnumType.STRING)
	@Column(name="genre")
	private MovieGenre genre;
	
	@Column(name="runtime")
	private int runtime;
	
	@Column(name="minimal_age")
	private int minimalAge;
	
	@Enumerated(value=EnumType.STRING)
	@Column(name="version")
	private MovieVersion version;
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MovieGenre getGenre() {
		return genre;
	}

	public void setGenre(MovieGenre genre) {
		this.genre = genre;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getMinimalAge() {
		return minimalAge;
	}

	public void setMinimalAge(int minimalAge) {
		this.minimalAge = minimalAge;
	}

	public MovieVersion getVersion() {
		return version;
	}

	public void setVersion(MovieVersion version) {
		this.version = version;
	}
}
