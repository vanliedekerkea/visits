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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Price originalPrice;

	@ManyToOne
	@JoinColumn(name = "VISIT_ID", nullable = false, referencedColumnName = "ID")
	private Visit linkedVisit;

	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private LocalDateTime sellDateTime;

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

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public Price getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Price originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Visit getLinkedVisit() {
		return linkedVisit;
	}

	public void setLinkedVisit(Visit linkedVisit) {
		this.linkedVisit = linkedVisit;
	}

	public LocalDateTime getSellDateTime() {
		return sellDateTime;
	}

	public void setSellDateTime(LocalDateTime sellDateTime) {
		this.sellDateTime = sellDateTime;
	}

}
