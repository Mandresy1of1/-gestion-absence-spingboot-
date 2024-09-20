package com.hei.absence.gestion.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Justificatif {
    private Long id;
    private Long absenceId;
    private String description;
    private LocalDate dateSoumission;
}