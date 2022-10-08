package com.office.visits.dto.bill.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.visits.dto.bill.BillDTO;
import com.office.visits.model.Bill;
import com.office.visits.model.Person;
import com.office.visits.repositories.PersonRepository;

@Component
public class BillDTOToBill implements Function<BillDTO, Bill> {

	@Autowired
	PersonRepository personRepository;

	@Override
	public Bill apply(BillDTO billDTO) {
		Bill bill = new Bill();
		Person person = personRepository.getReferenceById(billDTO.getPersonId());
		bill.setCreateDateTime(billDTO.getCreateDateTime());
		bill.setPerson(person);
		return bill;
	}

}
