package com.office.visits.services.interfaces;

import java.util.List;

public interface GetAllSave<T> {

	List<T> getAll();

	T save(T itemToSave);

}