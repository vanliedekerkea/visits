package com.office.visits.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.office.visits.model.enums.CustomerLevel;

@Entity
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_generator")
	@SequenceGenerator(name = "price_generator", sequenceName = "price_seq", allocationSize = 50)
	private long id;

	@Enumerated(EnumType.STRING)
	private CustomerLevel customerLevel;

	private BigDecimal price;

	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false, referencedColumnName = "ID")
	@JsonBackReference
	private Product product;

	@OneToMany(mappedBy = "price", cascade = CascadeType.ALL)
	private Set<PriceItem> priceItems = Collections.emptySet();
	
	public CustomerLevel getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(CustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal defaultPrice) {
		this.price = defaultPrice;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<PriceItem> getPriceItems() {
		return priceItems;
	}

	public void setPriceItems(Set<PriceItem> priceItems) {
		this.priceItems = priceItems;
	}

}
