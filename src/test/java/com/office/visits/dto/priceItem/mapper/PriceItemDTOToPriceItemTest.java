package com.office.visits.dto.priceItem.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.Price;
import com.office.visits.model.PriceItem;
import com.office.visits.model.Visit;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.repositories.VisitRepository;

@ExtendWith(SpringExtension.class)
class PriceItemDTOToPriceItemTest {

	@InjectMocks
	private PriceItemDTOToPriceItem priceItemDTOToPriceItem;

	@Mock
	VisitRepository visitRepository;

	@Mock
	PriceRepository priceRepository;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testPriceItemDTOToPriceItemNotNull() {
		assertNotNull(priceItemDTOToPriceItem);
	}

	@Test
	void testApply() {
		PriceItemDTO priceItemDTO = new PriceItemDTO();
		final long PRICE_ID = 10;
		final long VISIT_ID = 20;
		final long PRICE_ITEM_ID = 30;
		final BigDecimal FINAL_PRICE = new BigDecimal(19.90);
		final BigDecimal QUANTITY = new BigDecimal(1);
		priceItemDTO.setPriceId(PRICE_ID);
		priceItemDTO.setVisitId(VISIT_ID);
		priceItemDTO.setPriceItemId(PRICE_ITEM_ID);
		priceItemDTO.setFinalPrice(FINAL_PRICE);
		priceItemDTO.setQuantity(QUANTITY);
		Visit visitMock = new Visit();
		visitMock.setId(VISIT_ID);
		Price priceMock = new Price();
		priceMock.setId(PRICE_ID);
		Mockito.when(visitRepository.getReferenceById(VISIT_ID)).thenReturn(visitMock);
		Mockito.when(priceRepository.getReferenceById(PRICE_ID)).thenReturn(priceMock);
		PriceItem priceItem = Optional.of(priceItemDTO).map(priceItemDTOToPriceItem).get();
		assertNotNull(priceItem);
		assertEquals(PRICE_ITEM_ID, priceItem.getId());
		assertEquals(FINAL_PRICE, priceItem.getFinalPrice());
		assertEquals(QUANTITY, priceItem.getQuantity());
		assertEquals(PRICE_ID, priceItem.getPrice().getId());
		assertEquals(VISIT_ID, priceItem.getVisit().getId());
	}

}
