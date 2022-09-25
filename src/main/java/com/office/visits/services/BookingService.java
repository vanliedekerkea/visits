package com.office.visits.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.dto.booking.BookingDTO;
import com.office.visits.dto.booking.mapper.BookingDTOToBooking;
import com.office.visits.dto.booking.mapper.BookingToBookingDTO;
import com.office.visits.model.Booking;
import com.office.visits.repositories.BookingRepository;
import com.office.visits.repositories.PersonRepository;
import com.office.visits.repositories.VisitRepository;
import com.office.visits.services.interfaces.CRUD;

@Service
public class BookingService implements CRUD<BookingDTO> {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	VisitRepository visitRepository;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	BookingDTOToBooking bookingDTOToBooking;
	
	@Autowired
	BookingToBookingDTO bookingToBookingDTO;

	@Override
	public List<BookingDTO> getAll() {
		return bookingRepository.findAll().stream().map(bookingToBookingDTO).toList();
	}

	@Transactional
	@Override
	public BookingDTO save(BookingDTO bookingDTO) {
		Booking booking = bookingRepository
				.save(Stream.of(bookingDTO).map(bookingDTOToBooking).findFirst()
						.orElseThrow(() -> new MappingException("Unable to map BookingDTO")));
		return Stream.of(booking).map(bookingToBookingDTO).findFirst()
				.orElseThrow(() -> new MappingException("Unable to map Booking"));
	}

	@Override
	public Optional<BookingDTO> getById(Long id) {
		return bookingRepository.findById(id).map(bookingToBookingDTO);
	}

	@Override
	public void deleteById(Long id) {
		bookingRepository.deleteById(id);
	}

	@Transactional
	@Override
	public BookingDTO update(Long id, BookingDTO bookingToUpdate) {
		Booking bookingFromDB = bookingRepository.getReferenceById(id);
		if (bookingFromDB != null) {
			bookingFromDB.setPerson(personRepository.getReferenceById(bookingToUpdate.getPersonId()));
			bookingFromDB.setVisit(visitRepository.getReferenceById(bookingToUpdate.getVisitId()));
			return Stream.of(bookingRepository.save(bookingFromDB)).map(bookingToBookingDTO).findFirst()
					.orElseThrow(() -> new MappingException("Unable to map Booking"));
		} else {
			return null;
		}
	}
}
