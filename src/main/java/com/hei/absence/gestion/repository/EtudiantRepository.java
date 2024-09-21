package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Etudiant;

import java.util.List;

public interface EtudiantRepository {
    List<Etudiant> findAll();
    void save(Etudiant etudiant);
    Etudiant findById(String id);
    void update(Etudiant etudiant);
    void delete(String id);
}
