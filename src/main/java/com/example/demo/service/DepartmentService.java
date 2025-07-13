package com.example.demo.service;

import com.example.demo.command.CreateDepartmentCommand;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.DepartmentTreeDto;
import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.DepartmentDetail;
import com.example.demo.entity.DepartmentHead;
import com.example.demo.repository.DepartmentDetailRepository;
import com.example.demo.repository.DepartmentHeadRepository;
import com.example.demo.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentHeadRepository departmentHeadRepository;

    @Autowired
    private DepartmentDetailRepository departmentDetailRepository;

    @Transactional
    public void createDepartment(CreateDepartmentCommand command) {
        // Save department
        Department dept = new Department();
        dept.setDepartmentId(command.getDepartmentId());
        dept.setDepartmentName(command.getDepartmentName());
        dept.setOrgCode(command.getOrgCode());
        departmentRepository.save(dept);

        // Save department head
        DepartmentHead head = new DepartmentHead();
        head.setDepartmentId(command.getDepartmentId());
        head.setEmployeeId(command.getHeadEmployeeId());
        departmentHeadRepository.save(head);

        // Save employees in department
        for (String empId : command.getEmployeeIds()) {
            DepartmentDetail detail = new DepartmentDetail();
            detail.setDepartmentId(command.getDepartmentId());
            detail.setEmployeeId(empId);
            departmentDetailRepository.save(detail);
        }
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Object[]> rawResult = departmentRepository.getAllDepartmentsRaw();
        List<DepartmentDto> dtoList = new ArrayList<>();

        for (Object[] row : rawResult) {
            DepartmentDto dto = DepartmentDto.builder()
                    .departmentId((String) row[0])
                    .departmentName((String) row[1])
                    .orgCode((String) row[2])
                    .parentId((String) row[3])
                    .departmentHead((String) row[4])
                    .build();
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<DepartmentTreeDto> buildDepartmentTree(List<DepartmentDto> flatList) {
        Map<String, DepartmentTreeDto> map = new HashMap<>();
        List<DepartmentTreeDto> roots = new ArrayList<>();

        // build node
        for (DepartmentDto dto : flatList) {
            DepartmentTreeDto node = DepartmentTreeDto.builder()
                    .departmentId(dto.getDepartmentId())
                    .departmentName(dto.getDepartmentName())
                    .departmentHead(dto.getDepartmentHead())
                    .build();
            map.put(dto.getDepartmentId(), node);
        }

        // combine parent-child with org_code (parentId)
        for (DepartmentDto dto : flatList) {
            DepartmentTreeDto node = map.get(dto.getDepartmentId());
            if (dto.getParentId() == null) {
                roots.add(node); // root
            } else {
                DepartmentTreeDto parent = map.get(dto.getParentId());
                if (parent != null) {
                    parent.getSubDepartment().add(node);
                }
            }
        }
        return roots;
    }
}
