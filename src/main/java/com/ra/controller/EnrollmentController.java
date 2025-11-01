package com.ra.controller;

import com.ra.model.dto.EnrollmentDTO;
import com.ra.model.entity.Enrollment;
import com.ra.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments(){
        return new ResponseEntity<>(enrollmentService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO){
        Enrollment enrollment = enrollmentService.add(enrollmentDTO);
        if(enrollment != null){
            return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("add enrollment is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateEnrollment(@RequestBody EnrollmentDTO enrollmentDTO, @PathVariable Long id){
        Enrollment enrollment = enrollmentService.update(id, enrollmentDTO);
        if(enrollment != null){
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update enrollment is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id){
        boolean deleteEnrollment = enrollmentService.deleteById(id);
        if(deleteEnrollment){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("delete enrollment is failed",HttpStatus.BAD_REQUEST);
        }
    }
}
