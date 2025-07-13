package com.example.demo.controller;

import com.example.demo.command.CreateDepartmentCommand;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.DepartmentTreeDto;
import com.example.demo.entity.EmployeeDetail;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combination of @Controller and @ResponseBody, returns JSON
@RequestMapping("/api/v1/department") // Base path for all endpoints in this controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public String createDepartment(@RequestBody CreateDepartmentCommand command) {
        departmentService.createDepartment(command);
        return "Department created successfully.";
    }

    @GetMapping("/tree")
    public ResponseEntity<List<DepartmentTreeDto>> getDepartmentTree() {
        List<DepartmentDto> flatList = departmentService.getAllDepartments();
        List<DepartmentTreeDto> tree = departmentService.buildDepartmentTree(flatList);
        return ResponseEntity.ok(tree);
    }

}
