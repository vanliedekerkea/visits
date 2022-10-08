package com.office.visits.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_generator")
	@SequenceGenerator(name = "bill_generator", sequenceName = "bill_seq", allocationSize = 50)
	@Column(name = "ID")
	private long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	Set<PriceItem> priceItems = Collections.emptySet();

	@ManyToOne
	@JoinColumn(name = "PERSON_ID", nullable = false, referencedColumnName = "ID")
	@JsonBackReference
	private Person person;

	private LocalDateTime createDateTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<PriceItem> getPriceItems() {
		return priceItems;
	}

	public void setPriceItems(Set<PriceItem> priceItems) {
		this.priceItems = priceItems;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

}
