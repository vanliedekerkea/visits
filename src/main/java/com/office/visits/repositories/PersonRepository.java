package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
