package com.ra.model.dto;

import com.ra.model.entity.Course;
import com.ra.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDTO {
    private Student student;
    private Course course;
    private Double score;
}
