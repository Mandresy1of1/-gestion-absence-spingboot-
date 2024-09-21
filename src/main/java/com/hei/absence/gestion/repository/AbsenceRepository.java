package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;

import java.util.List;

public interface AbsenceRepository {
    List<Absence> findAll();
    List<Absence> findAllByEtudiant(String etudiantId);
    void insert(Absence absence);
    Absence findById(Long id);
    void update(Absence absence);
    void delete(Long id);
}
