package com.office.visits.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.dto.bill.BillDTO;
import com.office.visits.dto.bill.mapper.BillDTOToBill;
import com.office.visits.dto.bill.mapper.BillToBillDTO;
import com.office.visits.model.Bill;
import com.office.visits.repositories.BillRepository;
import com.office.visits.repositories.PersonRepository;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.services.interfaces.CRUD;

@Service
public class BillService implements CRUD<BillDTO> {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	BillDTOToBill billDTOToBill;

	@Autowired
	BillToBillDTO billToBillDTO;

	@Override
	public List<BillDTO> getAll() {
		return billRepository.findAll().stream().map(billToBillDTO).toList();
	}

	@Transactional
	@Override
	public BillDTO save(BillDTO billDTO) {
		Bill bill = billRepository.save(Stream.of(billDTO).map(billDTOToBill).findFirst()
				.orElseThrow(() -> new MappingException("Unable to map BillDTO")));
		return Stream.of(bill).map(billToBillDTO).findFirst()
				.orElseThrow(() -> new MappingException("Unable to map Bill"));
	}

	@Override
	public Optional<BillDTO> getById(Long id) {
		return billRepository.findById(id).map(billToBillDTO);
	}

	@Override
	public void deleteById(Long id) {
		billRepository.deleteById(id);
	}

	@Transactional
	@Override
	public BillDTO update(Long id, BillDTO billToUpdate) {
		Bill billFromDB = billRepository.getReferenceById(id);
		if (billFromDB != null) {
			billFromDB.setCreateDateTime(billToUpdate.getCreateDateTime());
			billFromDB.setPerson(personRepository.getReferenceById(billToUpdate.getPersonId()));
			return Stream.of(billRepository.save(billFromDB)).map(billToBillDTO).findFirst()
					.orElseThrow(() -> new MappingException("Unable to map Bill"));
		} else {
			return null;
		}
	}
}
