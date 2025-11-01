package com.ra.controller;

import com.ra.model.dto.CourseDTO;
import com.ra.model.entity.Course;
import com.ra.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody CourseDTO courseDTO){
        Course newCourse = courseService.add(courseDTO);
        if(newCourse != null){
            return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("add course is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
        Course update =  courseService.update(id, courseDTO);
        if(update != null){
            return new ResponseEntity<>(update, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update course is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        boolean deleteById = courseService.deleteById(id);
        if(deleteById){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("delete course is failed",HttpStatus.BAD_REQUEST);
        }
    }
}
