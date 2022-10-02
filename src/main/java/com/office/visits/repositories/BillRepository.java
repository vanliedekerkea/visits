package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
