package com.hei.absence.gestion.controller;

import com.hei.absence.gestion.model.Etudiant;
import com.hei.absence.gestion.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @PostMapping
    public void createEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.createEtudiant(etudiant);
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable String id) { // Changement ici pour String
        return etudiantService.getEtudiantById(id);
    }

    @PutMapping("/{id}")
    public void updateEtudiant(@PathVariable String id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id); // Utilisation du setter
        etudiantService.updateEtudiant(etudiant);
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable String id) { // Changement ici pour String
        etudiantService.deleteEtudiant(id);
    }
}
