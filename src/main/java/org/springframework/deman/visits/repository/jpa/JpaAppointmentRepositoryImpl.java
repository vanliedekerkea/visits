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
package org.springframework.deman.visits.repository.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.deman.visits.model.Appointment;
import org.springframework.deman.visits.repository.AppointmentRepository;
import org.springframework.deman.visits.repository.PetRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the {@link PetRepository} interface.
 *
 * @author Arno Van Liedekerke
 * @since 22.4.2006
 */
@Repository
public class JpaAppointmentRepositoryImpl implements AppointmentRepository {

    @PersistenceContext
    private EntityManager em;

	@Override
	public void save(Appointment appointment) throws DataAccessException {
		if (appointment.getId() == null) {
    		this.em.persist(appointment);     		
    	}
    	else {
    		this.em.merge(appointment);    
    	}
	}

	@Override
	public Appointment findById(Integer appointmentId) {
        return this.em.find(Appointment.class, appointmentId);
	}

	@Override
    @SuppressWarnings("unchecked")
	public Collection<Appointment> findAll() {
		return this.em.createQuery("SELECT a FROM Appointment a ORDER BY dateTime DESC").getResultList();
	}

}
