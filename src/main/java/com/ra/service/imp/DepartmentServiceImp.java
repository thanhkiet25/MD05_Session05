package com.ra.service.imp;
import com.ra.model.dto.DepartmentDTO;
import com.ra.model.entity.Department;
import com.ra.repository.DepartmentRepository;
import com.ra.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department add(DepartmentDTO departmentDTO) {
        try{
            return departmentRepository.save(convertDepartmentDTOtoDepartment(departmentDTO));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Department findById(Long id) {
       return  departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("department not found"));
    }

    @Override
    public Department update(Long id, DepartmentDTO departmentDTO) {
        Department department = findById(id);
        if(department != null){
            Department updateDepartment = convertDepartmentDTOtoDepartment(departmentDTO);
            try{
              return  departmentRepository.save(updateDepartment);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Department department = findById(id);
        if(department != null){
            try{
                departmentRepository.delete(department);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
    public Department convertDepartmentDTOtoDepartment(DepartmentDTO departmentDTO) {
        return Department
                .builder()
                .departmentName(departmentDTO.getDepartmentName())
                .description(departmentDTO.getDescription())
                .build();

    }
}
