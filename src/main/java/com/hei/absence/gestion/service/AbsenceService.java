package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.repository.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    // Récupérer toutes les absences
    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    // Récupérer les absences par étudiant
    public List<Absence> getAbsencesByEtudiant(String etudiantId) {
        return absenceRepository.findAllByEtudiant(etudiantId);
    }

    // Ajouter une nouvelle absence
    public void addAbsence(Absence absence) {
        absenceRepository.insert(absence);
    }

    // Mettre à jour une absence
    public void updateAbsence(Absence absence) {
        Absence existingAbsence = absenceRepository.findById(absence.getId());
        if (existingAbsence == null) {
            throw new RuntimeException("Absence non trouvée avec l'ID: " + absence.getId());
        }
        absenceRepository.update(absence);
    }

    // Supprimer une absence par ID
    public void deleteAbsence(Long id) {
        Absence existingAbsence = absenceRepository.findById(id);
        if (existingAbsence == null) {
            throw new RuntimeException("Absence non trouvée avec l'ID: " + id);
        }
        absenceRepository.delete(id);
    }
}
