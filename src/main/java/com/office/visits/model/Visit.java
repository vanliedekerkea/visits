package com.office.visits.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	private Integer durationInMinutes;
	
	private String title;
	
	public Long getId() {
		return this.id;
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

}
