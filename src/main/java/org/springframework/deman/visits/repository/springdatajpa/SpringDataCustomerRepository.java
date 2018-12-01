/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.deman.visits.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.deman.visits.model.Customer;
import org.springframework.deman.visits.repository.CustomerRepository;

/**
 * Spring Data JPA specialization of the {@link CustomerRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataCustomerRepository extends CustomerRepository, Repository<Customer, Integer> {
		
		@Override
	    @Query("SELECT DISTINCT customer FROM Customer customer left join fetch customer.pets WHERE customer.lastName LIKE :lastName%")
	    public Collection<Customer> findByLastName(@Param("lastName") String lastName);
		
		@Override
		@Query("SELECT customer FROM Customer customer left join fetch customer.pets WHERE customer.id =:id")
	    public Customer findById(@Param("id") int id);
}
