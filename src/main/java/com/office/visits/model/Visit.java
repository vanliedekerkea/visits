package com.office.visits.model;

import java.time.Duration;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;


@DynamicUpdate
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Date date;
	private Duration duration;
	private String title;
	
	public Visit() {
		this.date = new Date();
		this.duration = Duration.ofHours(1);
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
