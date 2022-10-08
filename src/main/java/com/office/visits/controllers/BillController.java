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

import com.office.visits.dto.bill.BillDTO;
import com.office.visits.services.interfaces.CRUD;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	CRUD<BillDTO> billService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillDTO>> findAll() {
		return new ResponseEntity<>(billService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO newBill) {
		BillDTO bill = billService.save(newBill);
		if (bill != null) {
			return new ResponseEntity<>(bill, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Bill not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillDTO> getBill(@PathVariable("id") Long id) {
		Optional<BillDTO> optionalBill = billService.getById(id);
		if (optionalBill.isPresent()) {
			return new ResponseEntity<>(optionalBill.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<BillDTO> deleteBill(@PathVariable("id") Long id) {
		billService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillDTO> updateBill(@PathVariable("id") Long id, @RequestBody BillDTO billToUpdate) {
		return new ResponseEntity<>(billService.update(id, billToUpdate), HttpStatus.OK);
	}

}
