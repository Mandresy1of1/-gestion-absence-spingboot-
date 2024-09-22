package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;

import java.util.List;

public interface AbsenceRepository {
    List<Absence> findAll();
    boolean existsById(Long id);
    void insert(Absence absence);
    void update(Absence absence);
    void delete(Long id);
    List<Absence> findByEtudiantId(String etudiantId);
    List<Absence> findByCoursId(Long coursId);
    Absence findById(Long id);
    List<Absence> findAllByEtudiant(String etudiantId); // Ajout de la méthode
}
