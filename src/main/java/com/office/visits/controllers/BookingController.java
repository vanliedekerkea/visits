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

import com.office.visits.model.Booking;
import com.office.visits.services.interfaces.CRUD;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	CRUD<Booking> bookingService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Booking>> findAll() {
		return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking) {
		Booking booking = bookingService.save(newBooking);
		if (booking != null) {
			return new ResponseEntity<>(booking, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Booking not saved properly");
		}
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> getBooking(@PathVariable("id") Long id) {
		Optional<Booking> optionalBooking = bookingService.getById(id);
		if (optionalBooking.isPresent()) {
			return new ResponseEntity<>(optionalBooking.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable("id") Long id) {
		bookingService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @RequestBody Booking bookingToUpdate) {
		return new ResponseEntity<>(bookingService.update(id, bookingToUpdate), HttpStatus.OK);
	}

}
