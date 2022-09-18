package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Product;
import com.office.visits.repositories.ProductRepository;

@Service
public class ProductService implements DeleteGetUpdate<Product> {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product update(Long id, Product productToUpdate) {
		Product productFromDB = productRepository.getReferenceById(id);
		if (productFromDB != null) {
			productFromDB.setName(productToUpdate.getName());
			productFromDB.setProductType(productToUpdate.getProductType());
			return productRepository.save(productFromDB);
		} else {
			return null;
		}
	}
}
