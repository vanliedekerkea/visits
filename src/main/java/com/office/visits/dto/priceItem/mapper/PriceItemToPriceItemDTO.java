package com.office.visits.dto.priceItem.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.PriceItem;

@Component
public class PriceItemToPriceItemDTO implements Function<PriceItem, PriceItemDTO> {

	@Override
	public PriceItemDTO apply(PriceItem priceItem) {
		PriceItemDTO priceItemDTO = new PriceItemDTO();
		priceItemDTO.setId(priceItem.getId());
		priceItemDTO.setDiscount(priceItem.getDiscount());
		priceItemDTO.setDiscountComment(priceItem.getDiscountComment());
		priceItemDTO.setFinalPrice(priceItem.getFinalPrice());
		priceItemDTO.setQuantity(priceItem.getQuantity());
		priceItemDTO.setSellDateTime(priceItem.getSellDateTime());
		priceItemDTO.setSold(priceItem.getSold());
		priceItemDTO.setPriceId(priceItem.getPrice().getId());
		return priceItemDTO;
	}

}
