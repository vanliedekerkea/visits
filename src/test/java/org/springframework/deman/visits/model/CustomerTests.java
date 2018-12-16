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
package org.springframework.deman.visits.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.deman.visits.model.Customer;
import org.springframework.deman.visits.model.Pet;
import org.springframework.transaction.annotation.Transactional;

/**
 * JUnit test for the {@link Customer} class.
 *
 * @author Ken Krebs
 */
public class CustomerTests {

    @Test
    @Transactional
    public void testHasPet() {
        Customer customer = new Customer();
        Pet fido = new Pet();
        fido.setName("Fido");
        assertNull(customer.getPet("Fido"));
        assertNull(customer.getPet("fido"));
        customer.addPet(fido);
        assertEquals(fido, customer.getPet("Fido"));
        assertEquals(fido, customer.getPet("fido"));
    }
    
    @Test
    @Transactional
    public void testHasAppointment() {
        Customer customer = new Customer();
        Appointment appointment = new Appointment();
        appointment.setDescription("General checkup");
        appointment.setDateTime(new DateTime());
        assertNull(customer.getAppointments());
        customer.addAppointment(appointment);
        HashSet<Appointment> appointments = new HashSet<Appointment>();
        appointments.add(appointment); 
        assertEquals(appointments.toString(), customer.getAppointments().toString());
    }

}
