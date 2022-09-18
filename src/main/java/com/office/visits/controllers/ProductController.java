package com.office.visits.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.office.visits.model.Price;
import com.office.visits.model.Product;
import com.office.visits.services.PriceService;
import com.office.visits.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	PriceService priceService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findAll() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
		Product product = productService.save(newProduct);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		Optional<Product> optionalProduct = productService.getById(id);
		if (optionalProduct.isPresent()) {
			return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product productToUpdate) {
		return new ResponseEntity<>(productService.update(id, productToUpdate), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/prices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Price>> findAll(@PathVariable("id") Long priceId) {
		return new ResponseEntity<>(priceService.getAll(priceId), HttpStatus.OK);
	}

	@PostMapping(path = "{id}/prices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Price> createPrice(@PathVariable("id") Long productId, @RequestBody Price price) {
		return new ResponseEntity<>(priceService.save(productId, price), HttpStatus.OK);
	}

}
