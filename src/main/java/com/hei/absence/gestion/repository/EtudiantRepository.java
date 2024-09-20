package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Etudiant;

import java.util.List;

public interface EtudiantRepository {
    List<Etudiant> findAll();
    void save(Etudiant etudiant);
    Etudiant findById(String id); // Changement ici pour String
    void update(Etudiant etudiant);
    void delete(String id); // Changement ici pour String
}
