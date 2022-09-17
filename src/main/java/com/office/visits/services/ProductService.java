package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Product;
import com.office.visits.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	public Product updateProduct(Long id, Product productToUpdate) {
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
