package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Autowired // Assurez-vous d'utiliser cette annotation
    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public void addAbsence(Absence absence) {
        absenceRepository.insert(absence);
    }

    public void updateAbsence(Absence absence) {
        absenceRepository.update(absence);
    }

    public void deleteAbsence(Long id) {
        absenceRepository.delete(id);
    }

    public List<Absence> getAbsencesByCoursId(Long coursId) {
        return absenceRepository.findByCoursId(coursId);
    }

    public Absence getAbsenceById(Long id) {
        return absenceRepository.findById(id);
    }

    public List<Absence> getAbsencesByEtudiantId(String etudiantId) {
        return absenceRepository.findAllByEtudiant(etudiantId);
    }

    public List<Absence> getAbsencesByEtudiant(String etudiantId) {
        return absenceRepository.findAllByEtudiant(etudiantId);
    }
}
