package com.ra.service;

import com.ra.model.dto.GradeDTO;
import com.ra.model.entity.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findAll();

    Grade add(GradeDTO gradeDTO);

    Grade findById(Long id);

    Grade update(Long id,GradeDTO gradeDTO);

    boolean deleteById(Long id);
}
