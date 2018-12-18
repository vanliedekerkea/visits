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
package org.springframework.deman.visits.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.deman.visits.model.Appointment;
import org.springframework.deman.visits.model.Customer;
import org.springframework.deman.visits.model.Pet;
import org.springframework.deman.visits.model.PetType;
import org.springframework.deman.visits.model.Vet;
import org.springframework.deman.visits.model.Visit;
import org.springframework.deman.visits.service.ClinicService;
import org.springframework.deman.visits.util.EntityUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> Base class for {@link ClinicService} integration tests. </p> <p> Subclasses should specify Spring context
 * configuration using {@link ContextConfiguration @ContextConfiguration} annotation </p> <p>
 * AbstractclinicServiceTests and its subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * AbstractclinicServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li> An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary. </li> </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public abstract class AbstractClinicServiceTests {

    @Autowired
    protected ClinicService clinicService;

    @Test
    @Transactional
    public void findCustomers() {
        Collection<Customer> customers = this.clinicService.findCustomerByLastName("Davis");
        assertEquals(2, customers.size());
        customers = this.clinicService.findCustomerByLastName("Daviss");
        assertEquals(0, customers.size());
    }

    @Test
    public void findSingleCustomer() {
        Customer customer1 = this.clinicService.findCustomerById(1);
        assertTrue(customer1.getLastName().startsWith("Franklin"));
        Customer customer10 = this.clinicService.findCustomerById(10);
        assertEquals("Carlos", customer10.getFirstName());

        assertEquals(customer1.getPets().size(), 1);
    }
    
    @Test
    public void findSingleAppointment() {
    	Appointment appointment = this.clinicService.findAppointmentById(1);
    	assertTrue(appointment.getDescription().startsWith("Headache"));
    }
    
    @Test
    public void insertAppointment() {
    	Appointment appointment = new Appointment();
    	appointment.setDescription("Appointment test");
    	this.clinicService.saveAppointment(appointment);
        Assert.assertNotEquals("Appointment Id should have been generated", appointment.getId().longValue(), 0);
    }

    @Test
    @Transactional
    public void insertCustomer() {
        Collection<Customer> customers = this.clinicService.findCustomerByLastName("Schultz");
        int found = customers.size();
        Customer customer = new Customer();
        customer.setFirstName("Sam");
        customer.setLastName("Schultz");
        customer.setAddress("4, Evans Street");
        customer.setCity("Wollongong");
        customer.setTelephone("4444444444");
        this.clinicService.saveCustomer(customer);
        Assert.assertNotEquals("Customer Id should have been generated", customer.getId().longValue(), 0);
        customers = this.clinicService.findCustomerByLastName("Schultz");
        assertEquals("Verifying number of customers after inserting a new one.", found + 1, customers.size());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        Customer o1 = this.clinicService.findCustomerById(1);
        String old = o1.getLastName();
        o1.setLastName(old + "X");
        this.clinicService.saveCustomer(o1);
        o1 = this.clinicService.findCustomerById(1);
        assertEquals(old + "X", o1.getLastName());
    }

	@Test
	public void findPet() {
	    Collection<PetType> types = this.clinicService.findPetTypes();
	    Pet pet7 = this.clinicService.findPetById(7);
	    assertTrue(pet7.getName().startsWith("Samantha"));
	    assertEquals(EntityUtils.getById(types, PetType.class, 1).getId(), pet7.getType().getId());
	    assertEquals("Jean", pet7.getCustomer().getFirstName());
	    Pet pet6 = this.clinicService.findPetById(6);
	    assertEquals("George", pet6.getName());
	    assertEquals(EntityUtils.getById(types, PetType.class, 4).getId(), pet6.getType().getId());
	    assertEquals("Peter", pet6.getCustomer().getFirstName());
	}

	@Test
	public void getPetTypes() {
	    Collection<PetType> petTypes = this.clinicService.findPetTypes();
	
	    PetType petType1 = EntityUtils.getById(petTypes, PetType.class, 1);
	    assertEquals("cat", petType1.getName());
	    PetType petType4 = EntityUtils.getById(petTypes, PetType.class, 4);
	    assertEquals("snake", petType4.getName());
	}

	@Test
	@Transactional
	public void insertPet() {
	    Customer customer6 = this.clinicService.findCustomerById(6);
	    int found = customer6.getPets().size();
	    Pet pet = new Pet();
	    pet.setName("bowser");
	    Collection<PetType> types = this.clinicService.findPetTypes();
	    pet.setType(EntityUtils.getById(types, PetType.class, 2));
	    pet.setBirthDate(new DateTime());
	    customer6.addPet(pet);
	    assertEquals(found + 1, customer6.getPets().size());
	    // both storePet and storeCustomer are necessary to cover all ORM tools
	    this.clinicService.savePet(pet);
	    this.clinicService.saveCustomer(customer6);
	    customer6 = this.clinicService.findCustomerById(6);
	    assertEquals(found + 1, customer6.getPets().size());
	    assertNotNull("Pet Id should have been generated", pet.getId());
	}

	@Test
	@Transactional
	public void updatePet() throws Exception {
	    Pet pet7 = this.clinicService.findPetById(7);
	    String old = pet7.getName();
	    pet7.setName(old + "X");
	    this.clinicService.savePet(pet7);
	    pet7 = this.clinicService.findPetById(7);
	    assertEquals(old + "X", pet7.getName());
	}

	@Test
	public void findVets() {
	    Collection<Vet> vets = this.clinicService.findVets();
	
	    Vet v1 = EntityUtils.getById(vets, Vet.class, 2);
	    assertEquals("Leary", v1.getLastName());
	    assertEquals(1, v1.getNrOfSpecialties());
	    assertEquals("radiology", (v1.getSpecialties().get(0)).getName());
	    Vet v2 = EntityUtils.getById(vets, Vet.class, 3);
	    assertEquals("Douglas", v2.getLastName());
	    assertEquals(2, v2.getNrOfSpecialties());
	    assertEquals("dentistry", (v2.getSpecialties().get(0)).getName());
	    assertEquals("surgery", (v2.getSpecialties().get(1)).getName());
	}

	@Test
	@Transactional
	public void insertVisit() {
	    Pet pet7 = this.clinicService.findPetById(7);
	    int found = pet7.getVisits().size();
	    Visit visit = new Visit();
	    pet7.addVisit(visit);
	    visit.setDescription("test");
	    // both storeVisit and storePet are necessary to cover all ORM tools
	    this.clinicService.saveVisit(visit);
	    this.clinicService.savePet(pet7);
	    pet7 = this.clinicService.findPetById(7);
	    assertEquals(found + 1, pet7.getVisits().size());
	    assertNotNull("Visit Id should have been generated", visit.getId());
	}


}
