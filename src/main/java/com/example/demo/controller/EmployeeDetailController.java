package com.example.demo.controller;

import com.example.demo.dto.EmployeeDetailWithTeamDto;
import com.example.demo.entity.EmployeeDetail;
import com.example.demo.service.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Combination of @Controller and @ResponseBody, returns JSON
@RequestMapping("/api/v1/employee") // Base path for all endpoints in this controller
public class EmployeeDetailController {

    private final EmployeeDetailService employeeDetailService;

    @Autowired
    public EmployeeDetailController(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }
    @PostMapping
    public ResponseEntity<EmployeeDetail> createEmployee(@RequestBody EmployeeDetail employeeDetail) {
        EmployeeDetail createdEmployee = employeeDetailService.createEmployee(employeeDetail);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED); // Return 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getEmployeeByDepartmentId(@PathVariable String id) {
        InputStreamResource result = employeeDetailService.searchEmployeeByDepartmentId(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=active_employees.txt");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // Set content type to plain text

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.TEXT_PLAIN)
                .body(result);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<EmployeeDetailWithTeamDto> getEmployeeDetailWithTeam(@PathVariable String id) {
        EmployeeDetailWithTeamDto result = employeeDetailService.findEmployeeDetailWithTeam(id);
        return ResponseEntity.ok(result);
    }
}
