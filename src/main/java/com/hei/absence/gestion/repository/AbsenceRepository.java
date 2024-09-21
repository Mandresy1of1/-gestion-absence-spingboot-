package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;

import java.util.List;

public interface AbsenceRepository {
    List<Absence> findAll();
    void insert(Absence absence);
    void update(Absence absence);
    void delete(Long id);
    List<Absence> findByCoursId(Long coursId);
    Absence findById(Long id);
    List<Absence> findAllByEtudiant(String etudiantId);
}
