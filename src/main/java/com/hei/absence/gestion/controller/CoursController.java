package com.hei.absence.gestion.controller;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.service.AbsenceService;
import com.hei.absence.gestion.service.CoursService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {

    private final CoursService coursService;
    private final AbsenceService absenceService;

    public CoursController(CoursService coursService, AbsenceService absenceService) {
        this.coursService = coursService;
        this.absenceService = absenceService;
    }

    @GetMapping("/{id}/absences")
    public List<Absence> getAbsencesByCours(@PathVariable Long id) {
        return absenceService.getAbsencesByCoursId(id);
    }
}
