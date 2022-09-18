package com.office.visits.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.office.visits.model.Price;
import com.office.visits.services.DeleteGetUpdate;

@RestController
@RequestMapping("/prices")
public class PriceController {

	@Autowired
	DeleteGetUpdate<Price> priceDeleteGetUpdateService;

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Price> getPrice(@PathVariable("id") Long id) {
		Optional<Price> optionalPrice = priceDeleteGetUpdateService.getById(id);
		if (optionalPrice.isPresent()) {
			return new ResponseEntity<>(optionalPrice.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Price> deletePrice(@PathVariable("id") Long id) {
		priceDeleteGetUpdateService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Price> updatePrice(@PathVariable("id") Long id, @RequestBody Price priceToUpdate) {
		return new ResponseEntity<>(priceDeleteGetUpdateService.update(id, priceToUpdate), HttpStatus.OK);
	}

}
