package com.office.visits.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.office.visits.model.enums.CustomerLevel;
import com.office.visits.model.enums.Gender;
import com.office.visits.model.enums.PersonRole;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
	@SequenceGenerator(name = "person_generator", sequenceName = "person_seq", allocationSize = 50)
	@Column(name = "ID")
	private long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String name;

	private String surName;

	private String title;

	private Gender gender;

	private String email;

	private String phone;
	
	private PersonRole role;
	
	private CustomerLevel customerLevel;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	@JsonManagedReference
	private List<Address> addresses;

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public PersonRole getRole() {
		return role;
	}

	public void setRole(PersonRole role) {
		this.role = role;
	}

	public CustomerLevel getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(CustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}

}
