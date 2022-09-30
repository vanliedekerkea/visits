package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.PriceItem;

@Repository
public interface PriceItemRepository extends JpaRepository<PriceItem, Long> {

}
