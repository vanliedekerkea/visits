package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Person;
import com.office.visits.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public Optional<Person> getVisit(Long id) {
		return personRepository.findById(id);
	}

	public void deleteById(Long id) {
		personRepository.deleteById(id);
	}

	public Person updatePerson(Long id, Person personToUpdate) {
		Person personFromDB = personRepository.getReferenceById(id);
		if (personFromDB != null) {
			personFromDB.setDateOfBirth(personToUpdate.getDateOfBirth());
			personFromDB.setName(personToUpdate.getName());
			personFromDB.setSurName(personToUpdate.getSurName());
			personFromDB.setTitle(personToUpdate.getTitle());
			personFromDB.setGender(personToUpdate.getGender());
			personFromDB.setEmail(personToUpdate.getEmail());
			personFromDB.setPhone(personToUpdate.getPhone());
			personFromDB.setAddresses(personToUpdate.getAddresses());
			return personRepository.save(personFromDB);
		} else {
			return null;
		}
	}
}
