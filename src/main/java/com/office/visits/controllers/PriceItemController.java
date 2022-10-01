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

import com.office.visits.dto.priceItem.PriceItemDTO;
import com.office.visits.services.interfaces.CRUD;

@RestController
@RequestMapping("/price-items")
public class PriceItemController {

	@Autowired
	CRUD<PriceItemDTO> priceItemService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PriceItemDTO>> findAll() {
		return new ResponseEntity<>(priceItemService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceItemDTO> createPriceItem(@RequestBody PriceItemDTO newPriceItem) {
		PriceItemDTO priceItem = priceItemService.save(newPriceItem);
		if (priceItem != null) {
			return new ResponseEntity<>(priceItem, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "PriceItem not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceItemDTO> getPriceItem(@PathVariable("id") Long id) {
		Optional<PriceItemDTO> optionalPriceItem = priceItemService.getById(id);
		if (optionalPriceItem.isPresent()) {
			return new ResponseEntity<>(optionalPriceItem.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PriceItem not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<PriceItemDTO> deletePriceItem(@PathVariable("id") Long id) {
		priceItemService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceItemDTO> updatePriceItem(@PathVariable("id") Long id,
			@RequestBody PriceItemDTO priceItemToUpdate) {
		return new ResponseEntity<>(priceItemService.update(id, priceItemToUpdate), HttpStatus.OK);
	}

}
