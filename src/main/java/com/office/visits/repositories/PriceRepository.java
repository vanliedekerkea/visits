package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
