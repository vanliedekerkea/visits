package com.office.visits.dto.priceItem.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.model.Bill;
import com.office.visits.model.Price;
import com.office.visits.model.PriceItem;
import com.office.visits.model.Visit;

@ExtendWith(SpringExtension.class)
class PriceItemToPriceItemDTOTest {

	@Test
	void testApply() {
		final long VISIT_ID = 20;
		final long PRICE_ID = 10;
		final long PRICE_ITEM_ID = 30;
		final long BILL_ID = 40;
		final BigDecimal FINAL_PRICE = new BigDecimal(19.90);
		final BigDecimal QUANTITY = new BigDecimal(1);
		Visit visit = new Visit();
		visit.setId(VISIT_ID);
		Price price = new Price();
		price.setId(PRICE_ID);
		Bill bill = new Bill();
		bill.setId(BILL_ID);
		PriceItem priceItem = new PriceItem();
		priceItem.setVisit(visit);
		priceItem.setPrice(price);
		priceItem.setBill(bill);
		priceItem.setId(PRICE_ITEM_ID);
		priceItem.setFinalPrice(FINAL_PRICE);
		priceItem.setQuantity(QUANTITY);
		PriceItemDTO priceItemDTO = Optional.of(priceItem).map(new PriceItemToPriceItemDTO()).get();
		Assert.notNull(priceItemDTO, "PriceItemDTO is null");
		assertEquals(VISIT_ID, priceItemDTO.getVisitId());
		assertEquals(PRICE_ID, priceItemDTO.getPriceId());
		assertEquals(BILL_ID, priceItemDTO.getBillId());
		assertEquals(PRICE_ITEM_ID, priceItemDTO.getId());
		assertEquals(FINAL_PRICE, priceItemDTO.getFinalPrice());
		assertEquals(QUANTITY, priceItemDTO.getQuantity());
	}

}
