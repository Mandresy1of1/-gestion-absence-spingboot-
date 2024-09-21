package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Etudiant;
import com.hei.absence.gestion.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    // Créer un nouvel étudiant
    public void createEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    // Récupérer la liste de tous les étudiants
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par son ID personnalisé (STD23XXX)
    public Etudiant getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }

    // Mettre à jour un étudiant
    public void updateEtudiant(Etudiant etudiant) {
        etudiantRepository.update(etudiant);
    }

    // Supprimer un étudiant par son ID personnalisé
    public void deleteEtudiant(String id) {
        etudiantRepository.delete(id);
    }
}
