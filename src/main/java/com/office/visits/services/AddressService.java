package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.office.visits.model.Address;
import com.office.visits.model.Person;
import com.office.visits.repositories.AddressRepository;
import com.office.visits.repositories.PersonRepository;

@Service
public class AddressService implements DeleteGetUpdate<Address> {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAll(Long id) {
		return personRepository.findById(id).stream().findFirst().orElseThrow(() -> new EmptyResultDataAccessException(
				String.format("No %s entity with id %s exists!", Person.class, id), 1)).getAddresses();
	}

	public Address save(Long personId, Address address) {
		Person person = personRepository.findById(personId).stream().findFirst()
				.orElseThrow(() -> new EmptyResultDataAccessException(
						String.format("No %s entity with id %s exists!", Person.class, personId), 1));
		address.setPerson(person);
		return addressRepository.save(address);
	}

	@Override
	public Optional<Address> getById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Address update(Long id, Address addressToUpdate) {
		Address addressFromDB = addressRepository.getReferenceById(id);
		if (addressFromDB != null) {
			addressFromDB.setAdditionalCivicNumber(addressToUpdate.getAdditionalCivicNumber());
			addressFromDB.setCity(addressToUpdate.getCity());
			addressFromDB.setCivicNumber(addressToUpdate.getCivicNumber());
			addressFromDB.setCountry(addressToUpdate.getCountry());
			addressFromDB.setPostalCode(addressToUpdate.getPostalCode());
			addressFromDB.setStreet(addressToUpdate.getStreet());
			return addressRepository.save(addressFromDB);
		} else {
			return null;
		}
	}

}
