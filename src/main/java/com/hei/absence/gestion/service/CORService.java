package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.COR;
import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.repository.AbsenceRepository;
import com.hei.absence.gestion.repository.CORRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CORService {

    private final AbsenceRepository absenceRepository; // Injection directe de AbsenceRepository
    private final CORRepository corRepository; // Injection de CORRepository

    @Autowired
    public CORService(AbsenceRepository absenceRepository, CORRepository corRepository) {
        this.absenceRepository = absenceRepository;
        this.corRepository = corRepository;
    }

    public void checkAndCreateCOR(String etudiantId) {
        List<Absence> absences = absenceRepository.findAllByEtudiant(etudiantId);
        long count = absences.stream().filter(absence -> !absence.isJustifiee()).count();

        if (count >= 3) {
            // Vérifier si un COR existe déjà pour éviter les doublons
            boolean corExists = corRepository.findAll().stream()
                    .anyMatch(cor -> cor.getEtudiantId().equals(etudiantId));
            if (!corExists) {
                COR cor = new COR();
                cor.setEtudiantId(etudiantId);
                cor.setDateConvocation(LocalDate.now());
                cor.setStatut("Engaged"); 

                corRepository.insert(cor); // Persister la COR en base de données
            }
        }
    }

    public List<COR> getAllCORs() {
        return corRepository.findAll();
    }
}
