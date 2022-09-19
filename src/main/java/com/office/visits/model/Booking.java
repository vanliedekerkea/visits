package com.office.visits.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_generator")
	@SequenceGenerator(name = "booking_generator", sequenceName = "booking_seq", allocationSize = 50)
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "VISIT_ID", nullable = false, referencedColumnName = "ID")
	private Visit visit;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID", nullable = false, referencedColumnName = "ID")
	private Person person;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
