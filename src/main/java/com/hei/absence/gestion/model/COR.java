package com.hei.absence.gestion.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class COR {
    private Long id;
    private Long etudiantId;
    private LocalDate dateConvocation;
    private String statut;
}