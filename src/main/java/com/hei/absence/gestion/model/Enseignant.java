package com.hei.absence.gestion.model;

import lombok.Data;

@Data
public class Enseignant {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
