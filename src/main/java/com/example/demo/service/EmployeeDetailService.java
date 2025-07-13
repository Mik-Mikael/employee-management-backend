package com.example.demo.service;

import com.example.demo.dto.EmployeeDetailWithTeamDto;
import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.dto.TeamMemberDto;
import com.example.demo.entity.EmployeeDetail;
import com.example.demo.repository.EmployeeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailService {

    private final EmployeeDetailRepository employeeDetailRepository;

    // Using constructor injection is a best practice
    @Autowired
    public EmployeeDetailService(EmployeeDetailRepository employeeDetailRepository) {
        this.employeeDetailRepository = employeeDetailRepository;
    }

    public EmployeeDetail createEmployee(EmployeeDetail employeeDetail) {
        // You could add validation or other logic here
        return employeeDetailRepository.save(employeeDetail);
    }

    public InputStreamResource searchEmployeeByDepartmentId(String departmentId)  {
        List<EmployeeSearchDto> employeeSearchDtoList = employeeDetailRepository.findActiveEmployeesWithDepartmentAndHead(departmentId);

        StringBuilder textContent = new StringBuilder();
        textContent.append("Active Employee Report\n");
        textContent.append("======================\n\n");

        // Add a header row for clarity
        textContent.append(String.format("%-15s %-30s %-15s %-15s %-30s %-30s\n",
                "Employee ID", "Name", "Mobile No", "Start Date", "Department Name", "Department Head"));
        textContent.append("------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // Format and append each employee's data
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (EmployeeSearchDto employee : employeeSearchDtoList) {
            String startDate = employee.getStartDate() != null ? employee.getStartDate().format(dateFormatter) : "N/A";
            String departmentHeadName = employee.getDepartmentHeadName() != null ? employee.getDepartmentHeadName() : "N/A";

            textContent.append(String.format("%-15s %-30s %-15s %-15s %-30s %-30s\n",
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getMobileNo(),
                    startDate,
                    employee.getDepartmentName(),
                    departmentHeadName));
        }

        // 3. Convert the text content to an InputStream
        InputStream inputStream = new ByteArrayInputStream(textContent.toString().getBytes(StandardCharsets.UTF_8));
        InputStreamResource resource = new InputStreamResource(inputStream);

        return resource;
    }

//    public List<EmployeeSearchWithTeamDto> searchEmployeeWithTeamByEmployeeId(String employeeId) {
//        return employeeDetailRepository.findEmployeeDetailsAndTeam(employeeId);
//    }

    public EmployeeDetailWithTeamDto findEmployeeDetailWithTeam(String employeeId) {
        Map<String, Object> mainInfo = employeeDetailRepository.findEmployeeMainInfo(employeeId);
        EmployeeDetailWithTeamDto response = new EmployeeDetailWithTeamDto();

        response.setEmployeeId((String) mainInfo.get("employeeId"));
        response.setName((String) mainInfo.get("name"));
        response.setMobileNo((String) mainInfo.get("mobileNo"));
        response.setStartDate(LocalDate.parse(mainInfo.get("startDate").toString()));
        response.setDepartmentName((String) mainInfo.get("departmentName"));
        response.setDepartmentHeadName((String) mainInfo.get("departmentHeadName"));

        // Load team members if head
        if (response.getName().equals(response.getDepartmentHeadName())) {
            List<Map<String, Object>> team = employeeDetailRepository.findTeamMembersIfHead(employeeId);
            List<TeamMemberDto> teamMembers = team.stream().map(row -> {
                TeamMemberDto tm = new TeamMemberDto();
                tm.setEmployeeId((String) row.get("employeeId"));
                tm.setName((String) row.get("name"));
                tm.setMobileNo((String) row.get("mobileNo"));
                return tm;
            }).collect(Collectors.toList());
            response.setTeamMembers(teamMembers);
        } else {
            response.setTeamMembers(Collections.emptyList());
        }

        return response;
    }
}
