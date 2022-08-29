package com.office.visits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.visits.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
