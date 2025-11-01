package com.ra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
