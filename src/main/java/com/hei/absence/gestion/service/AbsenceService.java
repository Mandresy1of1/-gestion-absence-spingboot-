package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.repository.AbsenceRepository;

import java.util.List;

public class AbsenceService {
    private final AbsenceRepository absenceRepository;

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
}
