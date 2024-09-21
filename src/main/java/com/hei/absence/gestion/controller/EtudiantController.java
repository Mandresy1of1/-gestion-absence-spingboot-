package com.hei.absence.gestion.controller;

import com.hei.absence.gestion.model.Etudiant;
import com.hei.absence.gestion.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    // Injection par constructeur
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // Créer un nouvel étudiant
    @PostMapping
    public ResponseEntity<String> createEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.createEtudiant(etudiant);
        return new ResponseEntity<>("Étudiant créé avec succès", HttpStatus.CREATED);
    }

    // Récupérer tous les étudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    // Récupérer un étudiant par son ID (ex: stdXXXX)
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable String id) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        if (etudiant != null) {
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEtudiant(@PathVariable String id, @RequestBody Etudiant etudiant) {
        // Vérifier que l'étudiant existe avant de le mettre à jour
        Etudiant existingEtudiant = etudiantService.getEtudiantById(id);
        if (existingEtudiant != null) {
            etudiant.setId(id);  // S'assurer que l'ID correspond au path variable
            etudiantService.updateEtudiant(etudiant);
            return new ResponseEntity<>("Étudiant mis à jour avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Étudiant non trouvé", HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable String id) {
        // Vérifier que l'étudiant existe avant de le supprimer
        Etudiant existingEtudiant = etudiantService.getEtudiantById(id);
        if (existingEtudiant != null) {
            etudiantService.deleteEtudiant(id);
            return new ResponseEntity<>("Étudiant supprimé avec succès", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Étudiant non trouvé", HttpStatus.NOT_FOUND);
        }
    }
}
