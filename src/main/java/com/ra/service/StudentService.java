package com.ra.service;

import com.ra.model.dto.StudentDTO;
import com.ra.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student add(StudentDTO studentDTO);

    Student update(Long id,StudentDTO studentDTO);

    Student findById(Long id);

    boolean deleteById(Long id);
}
