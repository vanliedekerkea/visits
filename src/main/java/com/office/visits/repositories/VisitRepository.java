package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
