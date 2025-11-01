package com.ra.service;

import com.ra.model.dto.EnrollmentDTO;
import com.ra.model.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> findAll();

    Enrollment add(EnrollmentDTO enrollmentDTO);

    Enrollment findById(Long id);

    Enrollment update(Long id,EnrollmentDTO enrollmentDTO);

    boolean deleteById(Long id);
}
