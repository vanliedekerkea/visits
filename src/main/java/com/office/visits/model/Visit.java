package com.office.visits.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_generator")
	@SequenceGenerator(name = "visit_generator", sequenceName = "visit_seq", allocationSize = 50)
	private long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private Integer durationInMinutes;

	private String title;

	private boolean hasTakenPlace;

	@Lob
	private String note;

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getHasTakenPlace() {
		return hasTakenPlace;
	}

	public void setHasTakenPlace(boolean hasTakenPlace) {
		this.hasTakenPlace = hasTakenPlace;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
