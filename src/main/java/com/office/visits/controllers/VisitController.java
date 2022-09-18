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

import com.office.visits.model.Visit;
import com.office.visits.services.interfaces.CRUD;

@RestController
@RequestMapping("/visits")
public class VisitController {

	@Autowired
	CRUD<Visit> visitService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Visit>> findAll() {
		return new ResponseEntity<>(visitService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Visit> createVisit(@RequestBody Visit newVisit) {
		Visit visit = visitService.save(newVisit);
		if (visit != null) {
			return new ResponseEntity<>(visit, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Visit not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Visit> getVisit(@PathVariable("id") Long id) {
		Optional<Visit> optionalVisit = visitService.getById(id);
		if (optionalVisit.isPresent()) {
			return new ResponseEntity<>(optionalVisit.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visit not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Visit> deleteVisit(@PathVariable("id") Long id) {
		visitService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Visit> updateVisit(@PathVariable("id") Long id, @RequestBody Visit visitToUpdate) {
		return new ResponseEntity<>(visitService.update(id, visitToUpdate), HttpStatus.OK);
	}

}
