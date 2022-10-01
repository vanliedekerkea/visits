package com.office.visits.dto.priceItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PriceItemDTO {

	private long priceItemId;

	private BigDecimal finalPrice;

	private BigDecimal discount;

	private String discountComment;

	private BigDecimal quantity;

	private boolean isSold;

	private long priceId;

	private long visitId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime sellDateTime;

	public long getId() {
		return priceItemId;
	}

	public void setPriceItemId(long priceItemId) {
		this.priceItemId = priceItemId;
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

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long originalPriceId) {
		this.priceId = originalPriceId;
	}

	public long getVisitId() {
		return visitId;
	}

	public void setVisitId(long linkedVisitId) {
		this.visitId = linkedVisitId;
	}

	public LocalDateTime getSellDateTime() {
		return sellDateTime;
	}

	public void setSellDateTime(LocalDateTime sellDateTime) {
		this.sellDateTime = sellDateTime;
	}

}
