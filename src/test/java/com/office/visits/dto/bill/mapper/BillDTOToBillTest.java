package com.office.visits.dto.bill.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.office.visits.dto.bill.BillDTO;
import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.Bill;
import com.office.visits.model.Person;
import com.office.visits.model.PriceItem;
import com.office.visits.repositories.PersonRepository;

@ExtendWith(SpringExtension.class)
class BillDTOToBillTest {

	@InjectMocks
	private BillDTOToBill BillDTOToBill;

	@Mock
	PersonRepository personRepository;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBillDTOToBillNotNull() {
		assertNotNull(BillDTOToBill);
	}

	@Test
	void testApply() {
		final long PERSON_ID = 10;
		final long ID = 20;
		final long PRICE_ITEM_ID = 30;
		final LocalDateTime DATE_TIME = LocalDateTime.now();
		BillDTO billDTO = new BillDTO();
		PriceItemDTO priceItemDTO = new PriceItemDTO();
		priceItemDTO.setId(PRICE_ITEM_ID);
		PriceItem priceItem = new PriceItem();
		priceItem.setId(PRICE_ITEM_ID);
		Set<PriceItemDTO> priceItems = Collections.singleton(priceItemDTO);
		billDTO.setCreateDateTime(DATE_TIME);
		billDTO.setId(ID);
		billDTO.setPersonId(PERSON_ID);
		billDTO.setPriceItems(priceItems);
		Person personMock = new Person();
		personMock.setId(PERSON_ID);
		Mockito.when(personRepository.getReferenceById(PERSON_ID)).thenReturn(personMock);
		Bill bill = Optional.of(billDTO).map(BillDTOToBill).get();
		assertNotNull(bill);
		assertEquals(PERSON_ID, bill.getPerson().getId());
	}

}
