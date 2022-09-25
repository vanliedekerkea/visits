package com.office.visits.dto.booking.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.visits.dto.booking.BookingDTO;
import com.office.visits.model.Booking;
import com.office.visits.model.Person;
import com.office.visits.model.Visit;
import com.office.visits.repositories.PersonRepository;
import com.office.visits.repositories.VisitRepository;

@Component
public class BookingDTOToBooking implements Function<BookingDTO, Booking> {

	@Autowired
	VisitRepository visitRepository;

	@Autowired
	PersonRepository personRepository;

	@Override
	public Booking apply(BookingDTO bookingDTO) {
		Booking booking = new Booking();
		Visit visit = visitRepository.getReferenceById(bookingDTO.getVisitId());
		Person person = personRepository.getReferenceById(bookingDTO.getPersonId());
		booking.setVisit(visit);
		booking.setPerson(person);
		return booking;
	}

}
