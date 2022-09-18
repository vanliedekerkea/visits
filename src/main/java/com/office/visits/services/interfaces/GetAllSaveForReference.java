package com.office.visits.services.interfaces;

import java.util.List;

public interface GetAllSaveForReference<T> {

	List<T> getAll(Long referencedId);

	T save(Long referencedId, T itemToSave);

}