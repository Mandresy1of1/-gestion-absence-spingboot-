package com.hei.absence.gestion.model;

import lombok.Data;

@Data
public class Cours {
    private Long id;
    private String nom;
    private Long enseignantId;
}