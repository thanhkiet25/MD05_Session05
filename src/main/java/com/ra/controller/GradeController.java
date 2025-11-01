package com.ra.controller;

import com.ra.model.dto.GradeDTO;
import com.ra.model.entity.Grade;
import com.ra.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades(){
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addGrade(@RequestBody GradeDTO gradeDTO){
        Grade newGrade = gradeService.add(gradeDTO);
      if (newGrade != null){
          return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>("add grade id failed",HttpStatus.BAD_REQUEST);
      }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO){
        Grade updateGrade = gradeService.update(id, gradeDTO);
        if (updateGrade != null){
            return new ResponseEntity<>(updateGrade, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update grade id failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable Long id){
       boolean  delete =gradeService.deleteById(id);
       if (delete){
           return new ResponseEntity<>(HttpStatus.OK);
       }else{
           return new ResponseEntity<>("delete grade id failed",HttpStatus.BAD_REQUEST);
       }
    }
}
