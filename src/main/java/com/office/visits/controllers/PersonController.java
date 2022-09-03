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
import com.office.visits.services.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	PersonService personService;

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
		Optional<Person> optionalPerson = personService.getPerson(id);
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
		return new ResponseEntity<>(personService.updatePerson(id, personToUpdate), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> findAllPersonAddresses(@PathVariable("id") Long personId) {
		return new ResponseEntity<>(personService.getAllPersonAddress(personId), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getPersonAddress(@PathVariable("id") Long personId,
			@PathVariable("addressId") Long addressId) {
		return new ResponseEntity<>(personService.getPersonAddress(personId, addressId), HttpStatus.OK);
	}

	@PostMapping(path = "{id}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPersonAddress(@PathVariable("id") Long personId, @RequestBody Address address) {
		return new ResponseEntity<>(personService.createPersonAddress(personId, address), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> deletePersonAddress(@PathVariable("id") Long personId,
			@PathVariable("addressId") Long addressId) {
		return new ResponseEntity<>(personService.deletePersonAddress(personId, addressId), HttpStatus.OK);
	}

}
