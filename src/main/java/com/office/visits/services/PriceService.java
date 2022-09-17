package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.office.visits.model.Price;
import com.office.visits.model.Product;
import com.office.visits.repositories.PriceRepository;
import com.office.visits.repositories.ProductRepository;

@Service
public class PriceService {

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	ProductRepository productRepository;

	public List<Price> getAll(Long id) {
		return productRepository.findById(id).stream().findFirst().orElseThrow(() -> new EmptyResultDataAccessException(
				String.format("No %s entity with id %s exists!", Price.class, id), 1)).getPrices();
	}

	public Price save(Long productId, Price price) {
		Product product = productRepository.findById(productId).stream().findFirst()
				.orElseThrow(() -> new EmptyResultDataAccessException(
						String.format("No %s entity with id %s exists!", Product.class, productId), 1));
		price.setProduct(product);
		return priceRepository.save(price);
	}

	public Optional<Price> getPrice(Long id) {
		return priceRepository.findById(id);
	}

	public void deleteById(Long id) {
		priceRepository.deleteById(id);
	}

	public Price updatePrice(Long id, Price priceToUpdate) {
		Price priceFromDB = priceRepository.getReferenceById(id);
		if (priceFromDB != null) {
			priceFromDB.setCurrency(priceToUpdate.getCurrency());
			priceFromDB.setCustomerLevel(priceToUpdate.getCustomerLevel());
			priceFromDB.setPrice(priceToUpdate.getPrice());
			return priceRepository.save(priceFromDB);
		} else {
			return null;
		}
	}
}
