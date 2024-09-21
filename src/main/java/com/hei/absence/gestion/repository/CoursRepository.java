package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Cours;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository {
    List<Cours> findAll();
    void save(Cours cours);
    Cours findById(Long id);
    void update(Cours cours);
    void delete(Long id);
}
