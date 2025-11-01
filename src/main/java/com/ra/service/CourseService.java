package com.ra.service;

import com.ra.model.dto.CourseDTO;
import com.ra.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course add(CourseDTO courseDTO);

    Course findById(Long id);

    Course update(Long id,CourseDTO courseDTO);

    boolean deleteById(Long id);
}
