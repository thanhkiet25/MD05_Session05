package com.ra.repository;

import com.ra.model.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
