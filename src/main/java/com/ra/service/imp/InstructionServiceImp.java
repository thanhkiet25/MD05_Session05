package com.ra.service.imp;

import com.ra.model.dto.InstructionDTO;
import com.ra.model.entity.Instructor;
import com.ra.repository.InstructionRepository;
import com.ra.service.InstructionService;
import org.aspectj.apache.bcel.generic.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionServiceImp implements InstructionService {
    @Autowired
    private InstructionRepository instructionRepository;

    public Instructor convertInstructionToInstructor(InstructionDTO instructionDTO){
        return Instructor
                .builder()
                .email(instructionDTO.getEmail())
                .name(instructionDTO.getName())
                .department(instructionDTO.getDepartment())
                .build();
    }

    @Override
    public List<Instructor> findAll() {
        return instructionRepository.findAll();
    }

    @Override
    public Instructor add(InstructionDTO instructionDTO) {
        return instructionRepository.save(convertInstructionToInstructor(instructionDTO));
    }

    @Override
    public Instructor update(Long id, InstructionDTO instructionDTO) {
      Instructor instructor = findById(id);
      if(instructor != null){
          Instructor newInstruction = convertInstructionToInstructor(instructionDTO);
          newInstruction.setId(id);
          try{
              return instructionRepository.save(newInstruction);
          }catch(Exception e){
              e.printStackTrace();
              return null;
          }
      }else{
          return null;
      }
    }

    @Override
    public Instructor findById(Long id) {
        return  instructionRepository.findById(id).orElseThrow(()-> new RuntimeException("instructor not found"));
    }

    @Override
    public boolean deleteById(Long id) {
      Instructor instructor = findById(id);
      if(instructor != null){
          try{
              instructionRepository.deleteById(id);
              return true;
          }catch(Exception e){
              e.printStackTrace();
              return false;
          }
      }else{
          return false;
      }
    }
}
