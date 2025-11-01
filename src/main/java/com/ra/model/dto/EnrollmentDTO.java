package com.ra.model.dto;

import com.ra.model.entity.Course;
import com.ra.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnrollmentDTO {
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
}
