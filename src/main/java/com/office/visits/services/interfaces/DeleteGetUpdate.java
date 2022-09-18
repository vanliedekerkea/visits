package com.office.visits.services.interfaces;

import java.util.Optional;

public interface DeleteGetUpdate<T> {

	Optional<T> getById(Long id);

	void deleteById(Long id);

	T update(Long id, T itemToUpdate);

}