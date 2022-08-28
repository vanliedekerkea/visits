package com.office.visits.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.visits.model.Visit;
import com.office.visits.repositories.VisitRepository;

@Service
public class VisitService {

	@Autowired
	VisitRepository visitRepository;

	public List<Visit> getAll() {
		return visitRepository.findAll();
	}
	
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}
}
