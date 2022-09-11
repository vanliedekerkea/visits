package com.office.visits.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	public Optional<Visit> getVisit(Long id) {
		return visitRepository.findById(id);
	}

	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

	@Transactional
	public Visit updateVisit(Long id, Visit visitToUpdate) {
		Visit visitFromDB = visitRepository.getReferenceById(id);
		if (visitFromDB != null) {
			visitFromDB.setDate(visitToUpdate.getDate());
			visitFromDB.setDurationInMinutes(visitToUpdate.getDurationInMinutes());
			visitFromDB.setTitle(visitToUpdate.getTitle());
			visitFromDB.setHasTakenPlace(visitToUpdate.getHasTakenPlace());
			visitFromDB.setNote(visitToUpdate.getNote());
			return visitRepository.save(visitFromDB);
		} else {
			return null;
		}
	}
}
