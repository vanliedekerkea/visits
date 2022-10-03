package com.office.visits.dto.bill;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.office.visits.dto.priceItem.PriceItemDTO;

public class BillDTO {

	private long id;

	private Set<PriceItemDTO> priceItems = Collections.emptySet();

	private Long personId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<PriceItemDTO> getPriceItems() {
		return priceItems;
	}

	public void setPriceItems(Set<PriceItemDTO> priceItems) {
		this.priceItems = priceItems;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}
