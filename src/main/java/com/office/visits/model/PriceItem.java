package com.office.visits.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class PriceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_generator")
	@SequenceGenerator(name = "price_generator", sequenceName = "price_seq", allocationSize = 50)
	private long id;

	private BigDecimal finalPrice;

	private BigDecimal discount;

	private String discountComment;

	private BigDecimal quantity;

	private boolean isSold;

	@ManyToOne
	@JoinColumn(name = "PRICE_ID", nullable = false, referencedColumnName = "ID")
	private Price price;

	@ManyToOne
	@JoinColumn(name = "VISIT_ID", referencedColumnName = "ID") //it can be nullable
	private Visit visit;

	private LocalDateTime sellDateTime;
	
	@ManyToOne
	@JoinColumn(name = "BILL_ID", referencedColumnName = "ID") //it can be nullable
	private Bill bill;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getDiscountComment() {
		return discountComment;
	}

	public void setDiscountComment(String discountComment) {
		this.discountComment = discountComment;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public boolean getSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price originalPrice) {
		this.price = originalPrice;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit linkedVisit) {
		this.visit = linkedVisit;
	}

	public LocalDateTime getSellDateTime() {
		return sellDateTime;
	}

	public void setSellDateTime(LocalDateTime sellDateTime) {
		this.sellDateTime = sellDateTime;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
