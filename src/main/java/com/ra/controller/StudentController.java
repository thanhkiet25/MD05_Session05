package com.ra.controller;

import com.ra.model.dto.StudentDTO;
import com.ra.model.entity.Student;
import com.ra.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
        Student newStudent = studentService.add(studentDTO);
        if(newStudent != null){
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("add student is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        Student update = studentService.update(id, studentDTO);
        if(update != null){
            return new ResponseEntity<>(update, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update student is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        boolean delete = studentService.deleteById(id);
        if(delete){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("delete student is failed",HttpStatus.BAD_REQUEST);
        }
    }
}

