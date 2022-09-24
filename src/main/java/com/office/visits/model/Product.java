package com.office.visits.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.office.visits.model.enums.ProductType;

import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 50)
	private long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	@JsonManagedReference
	private List<Price> prices = Collections.emptyList();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

}
