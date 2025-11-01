package com.ra.controller;

import com.ra.model.dto.DepartmentDTO;
import com.ra.model.entity.Department;
import com.ra.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping

    public ResponseEntity<List<Department>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO){
        Department newDepartment = departmentService.add(departmentDTO);
        if(newDepartment != null){
            return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("add department is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
        Department updatedDepartment = departmentService.update(id, departmentDTO);
        if(updatedDepartment != null){
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update department is failed",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        boolean deleteDepartment = departmentService.deleteById(id);
        if(deleteDepartment){
            return new ResponseEntity<>("delete department is failed",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("delete department is failed",HttpStatus.BAD_REQUEST);
        }
    }
}
