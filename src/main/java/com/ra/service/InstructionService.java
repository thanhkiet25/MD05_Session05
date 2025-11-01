package com.ra.service;

import com.ra.model.dto.InstructionDTO;
import com.ra.model.entity.Instructor;
import org.aspectj.apache.bcel.generic.Instruction;

import java.util.List;

public interface InstructionService {
    List<Instructor> findAll();

    Instructor add(InstructionDTO instructionDTO);

    Instructor update(Long id,InstructionDTO instructionDTO);

    Instructor findById(Long id);

    boolean deleteById(Long id);
}
