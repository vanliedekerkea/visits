package com.office.visits.dto.booking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.office.visits.dto.booking.BookingDTO;
import com.office.visits.model.Booking;
import com.office.visits.model.Person;
import com.office.visits.model.Visit;

@ExtendWith(SpringExtension.class)
class BookingToBookingDTOTest {

	@Test
	void testAppXly() {
		Visit visit = new Visit();
		visit.setId(20L);
		Person person = new Person();
		person.setId(10L);
		Booking booking = new Booking();
		booking.setVisit(visit);
		booking.setPerson(person);
		BookingDTO bookingDTO = Optional.of(booking).map(new BookingToBookingDTO()).get();
		Assert.notNull(bookingDTO, "BookingDTO is null");
		assertEquals(20L, bookingDTO.getVisitId());
		assertEquals(10L, bookingDTO.getPersonId());
	}

}
