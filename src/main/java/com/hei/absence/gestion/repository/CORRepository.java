package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.COR;
import java.util.List;

public interface CORRepository {
    void insert(COR cor);
    List<COR> findAll();
    COR findById(Long id);
    void update(COR cor);
    void delete(Long id);
}
