package com.hei.absence.gestion.model;

import java.time.LocalDate;

public class Absence {
    private Long id;
    private Long etudiantId;
    private Long coursId;
    private LocalDate dateAbsence;
    private boolean estJustifiee;

    // Constructeur avec paramètres
    public Absence(Long id, Long etudiantId, Long coursId, LocalDate dateAbsence, boolean estJustifiee) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.coursId = coursId;
        this.dateAbsence = dateAbsence;
        this.estJustifiee = estJustifiee;
    }

    // Constructeur par défaut
    public Absence() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEtudiantId() { return etudiantId; }
    public void setEtudiantId(Long etudiantId) { this.etudiantId = etudiantId; }

    public Long getCoursId() { return coursId; }
    public void setCoursId(Long coursId) { this.coursId = coursId; }

    public LocalDate getDateAbsence() { return dateAbsence; }
    public void setDateAbsence(LocalDate dateAbsence) { this.dateAbsence = dateAbsence; }

    public boolean isEstJustifiee() { return estJustifiee; }
    public void setEstJustifiee(boolean estJustifiee) { this.estJustifiee = estJustifiee; }
}
