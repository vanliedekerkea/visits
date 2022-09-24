package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Booking;
import com.office.visits.repositories.BookingRepository;
import com.office.visits.services.interfaces.CRUD;

@Service
public class BookingService implements CRUD<Booking> {

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public List<Booking> getAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Optional<Booking> getById(Long id) {
		return bookingRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		bookingRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Booking update(Long id, Booking bookingToUpdate) {
		Booking bookingFromDB = bookingRepository.getReferenceById(id);
		if (bookingFromDB != null) {
			bookingFromDB.setPerson(bookingToUpdate.getPerson());
			bookingFromDB.setVisit(bookingToUpdate.getVisit());
			return bookingRepository.save(bookingFromDB);
		} else {
			return null;
		}
	}
}
