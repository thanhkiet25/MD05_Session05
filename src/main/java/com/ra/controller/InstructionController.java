package com.ra.controller;

import com.ra.model.dto.InstructionDTO;
import com.ra.model.entity.Instructor;
import com.ra.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructions")
public class InstructionController {
    @Autowired
    private InstructionService instructionService;
    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructions(){
        return new ResponseEntity<>(instructionService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addInstruction(@RequestBody InstructionDTO instructionDTO){
        Instructor newInstructor = instructionService.add(instructionDTO);
        if(newInstructor != null){
            return new ResponseEntity<>(newInstructor,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("add instruction is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editInstruction(@PathVariable Long id, @RequestBody InstructionDTO instructionDTO){
        Instructor instructor = instructionService.update(id, instructionDTO);
        if(instructor != null){
            return new ResponseEntity<>(instructor,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("edit instruction is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstruction(@PathVariable Long id){
      boolean delete=  instructionService.deleteById(id);
      if(delete){
          return new ResponseEntity<>(HttpStatus.OK);
      }else{
          return new ResponseEntity<>("delete instruction is failed",HttpStatus.BAD_REQUEST);
      }
    }
}
