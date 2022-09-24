package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
