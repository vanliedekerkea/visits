package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Address;
import com.office.visits.model.Person;
import com.office.visits.repositories.AddressRepository;
import com.office.visits.repositories.PersonRepository;

@Service
public class PersonService implements DeleteGetUpdate<Person> {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressRepository addressRepository;

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Person save(Person person) {
		for (Address a : person.getAddresses()) {
			if (a.getPerson() == null) {
				a.setPerson(person);
			}
		}
		return personRepository.save(person);
	}

	@Override
	public Optional<Person> getById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		personRepository.deleteById(id);
	}

	@Override
	public Person update(Long id, Person personToUpdate) {
		Person personFromDB = personRepository.getReferenceById(id);
		if (personFromDB != null) {
			personFromDB.setDateOfBirth(personToUpdate.getDateOfBirth());
			personFromDB.setName(personToUpdate.getName());
			personFromDB.setSurName(personToUpdate.getSurName());
			personFromDB.setTitle(personToUpdate.getTitle());
			personFromDB.setGender(personToUpdate.getGender());
			personFromDB.setEmail(personToUpdate.getEmail());
			personFromDB.setPhone(personToUpdate.getPhone());
			// A person's address should not be updated here
			return personRepository.save(personFromDB);
		} else {
			return null;
		}
	}

}
