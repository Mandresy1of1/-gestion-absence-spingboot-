package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final CORService corService; // Injection de CORService

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository, CORService corService) {
        this.absenceRepository = absenceRepository;
        this.corService = corService;
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public void addAbsence(Absence absence) {
        absenceRepository.insert(absence);

        // Vérifier si un COR doit être créé pour l'étudiant après cette absence
        corService.checkAndCreateCOR(absence.getEtudiantId());
    }

    public void updateAbsence(Absence absence) {
        if (absenceRepository.existsById(absence.getId())) {
            absenceRepository.update(absence);
        } else {
            throw new IllegalArgumentException("Absence non trouvée pour l'ID : " + absence.getId());
        }
    }

    public void deleteAbsence(Long id) {
        if (absenceRepository.existsById(id)) {
            absenceRepository.delete(id);
        } else {
            throw new IllegalArgumentException("Absence non trouvée pour l'ID : " + id);
        }
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

    // Implémentation correcte de la méthode getAbsencesByCours
    public List<Absence> getAbsencesByCours(Long coursId) {
        return absenceRepository.findByCoursId(coursId);
    }

    public boolean existsById(Long id) {
        return absenceRepository.existsById(id);
    }
}
