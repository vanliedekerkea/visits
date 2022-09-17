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

import com.office.visits.model.Address;
import com.office.visits.services.DeleteGetUpdate;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	DeleteGetUpdate<Address> addressDeleteGetUpdateService;

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
		Optional<Address> optionalAddress = addressDeleteGetUpdateService.getById(id);
		if (optionalAddress.isPresent()) {
			return new ResponseEntity<>(optionalAddress.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable("id") Long id) {
		addressDeleteGetUpdateService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id, @RequestBody Address addressToUpdate) {
		return new ResponseEntity<>(addressDeleteGetUpdateService.update(id, addressToUpdate), HttpStatus.OK);
	}

}
