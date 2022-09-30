package com.office.visits.dto.priceItem.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.Price;
import com.office.visits.model.PriceItem;
import com.office.visits.model.Visit;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.repositories.VisitRepository;

public class PriceItemDTOToPriceItem implements Function<PriceItemDTO, PriceItem> {

	@Autowired
	VisitRepository visitRepository;

	@Autowired
	PriceRepository priceRepository;

	@Override
	public PriceItem apply(PriceItemDTO priceItemDTO) {
		PriceItem priceItem = new PriceItem();
		Visit visit = visitRepository.getReferenceById(priceItemDTO.getLinkedVisitId());
		Price price = priceRepository.getReferenceById(priceItemDTO.getPriceItemId());
		priceItem.setLinkedVisit(visit);
		priceItem.setOriginalPrice(price);
		return priceItem;
	}

}
