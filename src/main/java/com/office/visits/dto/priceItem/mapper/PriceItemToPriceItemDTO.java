package com.office.visits.dto.priceItem.mapper;

import java.util.function.Function;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.PriceItem;

public class PriceItemToPriceItemDTO implements Function<PriceItem, PriceItemDTO> {

	@Override
	public PriceItemDTO apply(PriceItem priceItem) {
		PriceItemDTO priceItemDTO = new PriceItemDTO();
		priceItemDTO.setPriceItemId(priceItem.getId());
		priceItemDTO.setLinkedVisitId(priceItem.getLinkedVisit().getId());
		priceItemDTO.setOriginalPriceId(priceItem.getOriginalPrice().getId());
		return priceItemDTO;
	}

}
