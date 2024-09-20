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

    public void createEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }

    public void updateEtudiant(Etudiant etudiant) {
        etudiantRepository.update(etudiant);
    }

    public void deleteEtudiant(String id) {
        etudiantRepository.delete(id);
    }
}
