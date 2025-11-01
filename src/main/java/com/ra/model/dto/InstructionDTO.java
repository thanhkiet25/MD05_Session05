package com.ra.model.dto;

import com.ra.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructionDTO {
    private String name;
    private String email;
    private Department department;
}
