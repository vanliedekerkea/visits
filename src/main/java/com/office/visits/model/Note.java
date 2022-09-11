package com.office.visits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_generator")
	@SequenceGenerator(name = "note_generator", sequenceName = "note_seq", allocationSize = 50)
	private long id;
	
	@Lob
	private String notes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
