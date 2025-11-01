package com.ra.service.imp;

import com.ra.model.dto.EnrollmentDTO;
import com.ra.model.entity.Enrollment;
import com.ra.repository.EnrollmentRepository;
import com.ra.repository.StudentRepository;
import com.ra.service.CourseService;
import com.ra.service.EnrollmentService;
import com.ra.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImp implements EnrollmentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment add(EnrollmentDTO enrollmentDTO) {
        return  enrollmentRepository.save(convertEnrollmentDTOtoEnrollment(enrollmentDTO));
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Enrollment with id: " + id + " not found!"));
    }

    @Override
    public Enrollment update(Long id, EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = findById(id);
        if(enrollment != null){
            Enrollment updateEnrollment = convertEnrollmentDTOtoEnrollment(enrollmentDTO);
            updateEnrollment.setId(id);
            try{
                return enrollmentRepository.save(updateEnrollment);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Enrollment enrollment = findById(id);
        if(enrollment != null){
            try{
                enrollmentRepository.delete(enrollment);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    public Enrollment convertEnrollmentDTOtoEnrollment(EnrollmentDTO enrollmentDTO) {
        return Enrollment
                .builder()
                .student(studentService.findById(enrollmentDTO.getStudent().getId()))
                .course(courseService.findById(enrollmentDTO.getCourse().getId()))
                .enrollmentDate(enrollmentDTO.getEnrollmentDate())
                .build();

    }
}
