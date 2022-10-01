package com.office.visits.dto.priceItem.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.Price;
import com.office.visits.model.PriceItem;
import com.office.visits.model.Visit;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.repositories.VisitRepository;

@Component
public class PriceItemDTOToPriceItem implements Function<PriceItemDTO, PriceItem> {

	@Autowired
	VisitRepository visitRepository;

	@Autowired
	PriceRepository priceRepository;

	@Override
	public PriceItem apply(PriceItemDTO priceItemDTO) {
		PriceItem priceItem = new PriceItem();
		Visit visit = visitRepository.getReferenceById(priceItemDTO.getVisitId());
		Price price = priceRepository.getReferenceById(priceItemDTO.getPriceId());
		priceItem.setId(priceItemDTO.getId());
		priceItem.setVisit(visit);
		priceItem.setPrice(price);
		priceItem.setDiscount(priceItemDTO.getDiscount());
		priceItem.setDiscountComment(priceItemDTO.getDiscountComment());
		priceItem.setFinalPrice(priceItemDTO.getFinalPrice());
		priceItem.setVisit(visit);
		priceItem.setPrice(price);
		priceItem.setQuantity(priceItemDTO.getQuantity());
		priceItem.setSellDateTime(priceItemDTO.getSellDateTime());
		priceItem.setSold(priceItemDTO.getSold());
		return priceItem;
	}

}
