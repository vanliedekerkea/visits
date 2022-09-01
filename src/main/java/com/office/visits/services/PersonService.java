package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.office.visits.model.Address;
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

	public Optional<Person> getPerson(Long id) {
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
			// A person's address should not be updated here
			return personRepository.save(personFromDB);
		} else {
			return null;
		}
	}

	public List<Address> getAllPersonAddress(Long id) {
		return personRepository.findById(id).stream().findFirst().orElseThrow(() -> new EmptyResultDataAccessException(
				String.format("No %s entity with id %s exists!", Person.class, id), 1)).getAddresses();
	}

	public Address getPersonAddress(Long personId, Long addressId) {
		return this.getAllPersonAddress(personId).stream().filter(a -> addressId.equals(a.getId())).findFirst()
				.orElseThrow(() -> new EmptyResultDataAccessException(
						String.format("No %s entity with id %s exists!", Address.class, addressId), 1));
	}

}
