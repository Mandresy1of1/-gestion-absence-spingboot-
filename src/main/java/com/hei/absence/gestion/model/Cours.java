package com.hei.absence.gestion.model;

public class Cours {
    private Long id;
    private String nom;


    // Constructeur par défaut
    public Cours() {}

    // Constructeur
    public Cours(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
