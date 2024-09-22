package com.hei.absence.gestion.model;

import java.time.LocalDate;

public class Absence {
    private Long id;
    private String etudiantId;
    private LocalDate dateAbsence;
    private String motif;
    private boolean justifiee;
    private Long coursId; // Ajout de coursId


    public Absence() {}

    public Absence(Long id, String etudiantId, LocalDate dateAbsence, String motif, boolean justifiee, Long coursId) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.dateAbsence = dateAbsence;
        this.motif = motif;
        this.justifiee = justifiee;
        this.coursId = coursId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(String etudiantId) {
        this.etudiantId = etudiantId;
    }

    public LocalDate getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(LocalDate dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public boolean isJustifiee() {
        return justifiee;
    }

    public void setJustifiee(boolean justifiee) {
        this.justifiee = justifiee;
    }

    public Long getCoursId() {
        return coursId;
    }

    public void setCoursId(Long coursId) {
        this.coursId = coursId;
    }
}
