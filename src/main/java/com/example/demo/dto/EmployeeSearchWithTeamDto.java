package com.example.demo.dto;

import java.time.LocalDate;

public interface EmployeeSearchWithTeamDto {
    String getEmployeeId();
    String getName(); // Maps to 'name' alias in SQL
    String getMobileNo();
    LocalDate getStartDate();
    String getDepartmentName();
    String getDepartmentHeadName(); // Maps to 'department_head_name' alias in SQL
    String getRecordType(); // New field to distinguish main employee from team members
}
