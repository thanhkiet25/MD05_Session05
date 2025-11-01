package com.ra.service.imp;

import com.ra.model.dto.GradeDTO;
import com.ra.model.entity.Grade;
import com.ra.repository.GradeRepository;
import com.ra.service.CourseService;
import com.ra.service.GradeService;

import com.ra.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImp implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade add(GradeDTO gradeDTO) {
        try{
            return gradeRepository.save(convertGradeDTOToGrade(gradeDTO));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElseThrow(()->new RuntimeException("grade not found"));
    }

    @Override
    public Grade update(Long id, GradeDTO gradeDTO) {
        Grade grade = findById(id);
        if(grade!=null){
            Grade newGrade = convertGradeDTOToGrade(gradeDTO);
            newGrade.setId(id);
            try{
               return  gradeRepository.save(newGrade);
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
        Grade grade = findById(id);
        if(grade!=null){
            try{
                gradeRepository.delete(grade);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
    public Grade convertGradeDTOToGrade(GradeDTO gradeDTO){
        return Grade
                .builder()
                .student(studentService.findById(gradeDTO.getStudent().getId()))
                .course(courseService.findById(gradeDTO.getCourse().getId())).build();
    }
}
