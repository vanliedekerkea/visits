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

import com.office.visits.model.Address;
import com.office.visits.model.Person;
import com.office.visits.services.interfaces.CRUD;
import com.office.visits.services.interfaces.CRUDForReference;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	CRUD<Person> personService;

	@Autowired
	CRUDForReference<Address> addressService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> findAll() {
		return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
		Person person = personService.save(newPerson);
		if (person != null) {
			return new ResponseEntity<>(person, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Person not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
		Optional<Person> optionalPerson = personService.getById(id);
		if (optionalPerson.isPresent()) {
			return new ResponseEntity<>(optionalPerson.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
		personService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person personToUpdate) {
		return new ResponseEntity<>(personService.update(id, personToUpdate), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> findAllPersonAddresses(@PathVariable("id") Long personId) {
		return new ResponseEntity<>(addressService.getAll(personId), HttpStatus.OK);
	}

	@PostMapping(path = "{id}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> createPersonAddress(@PathVariable("id") Long personId,
			@RequestBody Address address) {
		return new ResponseEntity<>(addressService.save(personId, address), HttpStatus.OK);
	}

}
