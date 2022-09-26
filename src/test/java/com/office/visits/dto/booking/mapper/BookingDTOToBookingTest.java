package com.office.visits.dto.booking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.office.visits.dto.booking.BookingDTO;
import com.office.visits.model.Booking;
import com.office.visits.model.Person;
import com.office.visits.model.Visit;
import com.office.visits.repositories.PersonRepository;
import com.office.visits.repositories.VisitRepository;

@ExtendWith(SpringExtension.class)
class BookingDTOToBookingTest {

	@InjectMocks
	private BookingDTOToBooking bookingDTOToBooking;

	@Mock
	VisitRepository visitRepository;

	@Mock
	PersonRepository personRepository;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBookingDTOToBookingNotNull() {
		assertNotNull(bookingDTOToBooking);
	}

	@Test
	void testApply() {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setPersonId(10);
		bookingDTO.setVisitId(20);
		bookingDTO.setBookingId(1120);
		Visit visitMock = new Visit();
		visitMock.setId(20);
		Person personMock = new Person();
		personMock.setId(10);
		Mockito.when(visitRepository.getReferenceById(20L)).thenReturn(visitMock);
		Mockito.when(personRepository.getReferenceById(10L)).thenReturn(personMock);
		Booking booking = Optional.of(bookingDTO).map(bookingDTOToBooking).get();
		assertNotNull(booking);
		assertEquals(10, booking.getPerson().getId());
		assertEquals(20, booking.getVisit().getId());
	}
	
}
