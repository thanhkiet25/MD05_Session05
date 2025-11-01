package com.ra.service.imp;

import com.ra.model.dto.CourseDTO;
import com.ra.model.entity.Course;
import com.ra.repository.CourseRepository;
import com.ra.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course add(CourseDTO courseDTO) {
        try{
            return courseRepository.save(convertCourseDTOtoCourse(courseDTO));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findById(Long id) {
      return courseRepository.findById(id).orElseThrow(()->new RuntimeException("course not found"));
    }

    @Override
    public Course update(Long id, CourseDTO courseDTO) {
        Course course = findById(id);
        if(course!=null){
            Course updateCourse = convertCourseDTOtoCourse(courseDTO);
            updateCourse.setId(id);
            try{
               return  courseRepository.save(updateCourse);
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
        Course course = findById(id);
        if(course!=null){
            try{
                courseRepository.delete(course);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }

    }
    public Course convertCourseDTOtoCourse(CourseDTO courseDTO) {
        return Course
                .builder()
                .courseName(courseDTO.getCourseName())
                .description(courseDTO.getDescription())
                .duration(courseDTO.getDuration())
                .build();
    }
}
