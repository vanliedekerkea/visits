package com.office.visits.dto.bill.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.office.visits.dto.priceItem.mapper.PriceItemToPriceItemDTO;
import com.office.visits.model.Bill;
import com.office.visits.model.Person;
import com.office.visits.model.PriceItem;

@ExtendWith(SpringExtension.class)
class BillToBillDTOTest {

	@InjectMocks
	BillToBillDTO billToBillDTO;

	@Mock
	PriceItemToPriceItemDTO priceItemToPriceItemDTO;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBillToBillDTONotNull() {
		assertNotNull(priceItemToPriceItemDTO);
	}

	@Test
	void testApply() {
		final long PERSON_ID = 10;
		final long ID = 20;
		final long PRICE_ITEM_ID = 30;
		final LocalDateTime DATE_TIME = LocalDateTime.now();
		Bill bill = new Bill();
		PriceItem priceItem = new PriceItem();
		priceItem.setId(PRICE_ITEM_ID);
		PriceItemDTO priceItemDTO = new PriceItemDTO();
		priceItemDTO.setId(PRICE_ITEM_ID);
		Set<PriceItem> priceItems = Collections.singleton(priceItem);
		bill.setCreateDateTime(DATE_TIME);
		bill.setId(ID);
		Person person = new Person();
		person.setId(PERSON_ID);
		bill.setPerson(person);
		bill.setPriceItems(priceItems);
		Mockito.when(priceItemToPriceItemDTO.apply(priceItem)).thenReturn(priceItemDTO);
		BillDTO billDTO = Optional.of(bill).map(billToBillDTO).get();
		assertNotNull(billDTO);
		assertEquals(PERSON_ID, billDTO.getPersonId());
		assertEquals(PRICE_ITEM_ID, billDTO.getPriceItems().stream().collect(Collectors.toList()).get(0).getId());
	}

}
