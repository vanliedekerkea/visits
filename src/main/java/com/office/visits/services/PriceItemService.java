package com.office.visits.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.dto.priceItem.mapper.PriceItemDTOToPriceItem;
import com.office.visits.dto.priceItem.mapper.PriceItemToPriceItemDTO;
import com.office.visits.model.PriceItem;
import com.office.visits.repositories.PriceItemRepository;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.repositories.VisitRepository;
import com.office.visits.services.interfaces.CRUD;

@Service
public class PriceItemService implements CRUD<PriceItemDTO> {

	@Autowired
	PriceItemRepository priceItemRepository;

	@Autowired
	VisitRepository visitRepository;

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	PriceItemDTOToPriceItem priceItemDTOToPriceItem;

	@Autowired
	PriceItemToPriceItemDTO priceItemToPriceItemDTO;

	@Override
	public List<PriceItemDTO> getAll() {
		return priceItemRepository.findAll().stream().map(priceItemToPriceItemDTO).toList();
	}

	@Transactional
	@Override
	public PriceItemDTO save(PriceItemDTO priceItemDTO) {
		PriceItem priceItem = priceItemRepository.save(Stream.of(priceItemDTO).map(priceItemDTOToPriceItem).findFirst()
				.orElseThrow(() -> new MappingException("Unable to map PriceItemDTO")));
		return Stream.of(priceItem).map(priceItemToPriceItemDTO).findFirst()
				.orElseThrow(() -> new MappingException("Unable to map PriceItem"));
	}

	@Override
	public Optional<PriceItemDTO> getById(Long id) {
		return priceItemRepository.findById(id).map(priceItemToPriceItemDTO);
	}

	@Override
	public void deleteById(Long id) {
		priceItemRepository.deleteById(id);
	}

	@Transactional
	@Override
	public PriceItemDTO update(Long id, PriceItemDTO priceItemToUpdate) {
		PriceItem priceItemFromDB = priceItemRepository.getReferenceById(id);
		if (priceItemFromDB != null) {
			priceItemFromDB.setLinkedVisit(visitRepository.getReferenceById(priceItemToUpdate.getLinkedVisitId()));
			priceItemFromDB.setOriginalPrice(priceRepository.getReferenceById(priceItemToUpdate.getOriginalPriceId()));
			return Stream.of(priceItemRepository.save(priceItemFromDB)).map(priceItemToPriceItemDTO).findFirst()
					.orElseThrow(() -> new MappingException("Unable to map PriceItem"));
		} else {
			return null;
		}
	}
}
