package com.hei.absence.gestion.controller;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.service.AbsenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    // Créer une nouvelle absence
    @PostMapping
    public ResponseEntity<String> createAbsence(@RequestBody Absence absence) {
        absenceService.addAbsence(absence);
        return new ResponseEntity<>("Absence créée avec succès", HttpStatus.CREATED);
    }

    // Récupérer toutes les absences
    @GetMapping
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> absences = absenceService.getAllAbsences();
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    // Récupérer les absences d'un étudiant spécifique
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Absence>> getAbsencesByEtudiant(@PathVariable String etudiantId) {
        List<Absence> absences = absenceService.getAbsencesByEtudiantId(etudiantId);
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    // Récupérer les absences d'un cours spécifique
    @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<Absence>> getAbsencesByCours(@PathVariable Long coursId) {
        List<Absence> absences = absenceService.getAbsencesByCours(coursId);
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    // Mettre à jour une absence
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAbsence(@PathVariable Long id, @RequestBody Absence absence) {
        if (!absenceService.existsById(id)) {
            return new ResponseEntity<>("Absence non trouvée", HttpStatus.NOT_FOUND);
        }
        absence.setId(id); // Assurer que l'ID correspond au path variable
        absenceService.updateAbsence(absence);
        return new ResponseEntity<>("Absence mise à jour avec succès", HttpStatus.OK);
    }

    // Supprimer une absence
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAbsence(@PathVariable Long id) {
        if (!absenceService.existsById(id)) {
            return new ResponseEntity<>("Absence non trouvée", HttpStatus.NOT_FOUND);
        }
        absenceService.deleteAbsence(id);
        return new ResponseEntity<>("Absence supprimée avec succès", HttpStatus.NO_CONTENT);
    }
}
