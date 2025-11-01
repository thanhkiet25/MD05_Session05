package com.ra.service;

import com.ra.model.dto.DepartmentDTO;
import com.ra.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department add(DepartmentDTO departmentDTO);

    Department findById(Long id);

    Department update(Long id,DepartmentDTO departmentDTO);

    boolean deleteById(Long id);
}
