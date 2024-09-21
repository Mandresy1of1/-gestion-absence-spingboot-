package com.hei.absence.gestion.service;

import com.hei.absence.gestion.model.Cours;
import com.hei.absence.gestion.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public void createCours(Cours cours) {
        coursRepository.save(cours);
    }

    public void updateCours(Cours cours) {
        coursRepository.update(cours);
    }

    public void deleteCours(Long id) {
        coursRepository.delete(id);
    }
}
