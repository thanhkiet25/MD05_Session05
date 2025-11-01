package com.ra.service.imp;

import com.ra.model.dto.StudentDTO;
import com.ra.model.entity.Student;
import com.ra.repository.StudentRepository;
import com.ra.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student add(StudentDTO studentDTO) {
        return studentRepository.save(convertStudentDTOtoStudent(studentDTO));
    }

    @Override
    public Student update(Long id, StudentDTO studentDTO) {
        Student student = findById(id);
        if(student != null){
            Student updateStudent = convertStudentDTOtoStudent(studentDTO);
            updateStudent.setId(id);
            try{
               return studentRepository.save(updateStudent);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("student not found"));
    }

    @Override
    public boolean deleteById(Long id) {
        Student student = findById(id);
        if(student != null){
            try{
                studentRepository.delete(student);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }


    public Student convertStudentDTOtoStudent(StudentDTO studentDTO) {
        return Student
                .builder()
                .email(studentDTO.getEmail())
                .name(studentDTO.getName())
                .phoneNumber(studentDTO.getPhoneNumber())
                .build();
    }
}
