package com.office.visits.dto.bill.mapper;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.visits.dto.bill.BillDTO;
import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.dto.priceItem.mapper.PriceItemToPriceItemDTO;
import com.office.visits.model.Bill;

@Component
public class BillToBillDTO implements Function<Bill, BillDTO> {

	@Autowired
	PriceItemToPriceItemDTO priceItemToPriceItemDTO;

	@Override
	public BillDTO apply(Bill bill) {
		BillDTO billDTO = new BillDTO();
		Set<PriceItemDTO> priceItems = bill.getPriceItems().stream().map(priceItemToPriceItemDTO)
				.collect(Collectors.toSet());
		billDTO.setCreateDateTime(bill.getCreateDateTime());
		billDTO.setId(bill.getId());
		billDTO.setPersonId(bill.getPerson().getId());
		billDTO.setPriceItems(priceItems);
		return billDTO;
	}

}
