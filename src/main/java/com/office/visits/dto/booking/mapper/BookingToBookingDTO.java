package com.office.visits.dto.booking.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.office.visits.dto.booking.BookingDTO;
import com.office.visits.model.Booking;

@Component
public class BookingToBookingDTO implements Function<Booking, BookingDTO> {

	@Override
	public BookingDTO apply(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingId(booking.getId());
		bookingDTO.setVisitId(booking.getVisit().getId());
		bookingDTO.setPersonId(booking.getPerson().getId());
		return bookingDTO;
	}

}
