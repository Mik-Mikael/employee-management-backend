package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

//@Data
//public class EmployeeSearchDto {
//    private String employeeId;
//
//    private String employeeName;
//
//    private String mobileNo;
//
//    private LocalDate startDate;
//
//    private String departmentName;
//
//    private String headName;
//}

public interface EmployeeSearchDto {
    String getEmployeeId();
    String getName();
    String getMobileNo();
    LocalDate getStartDate();
    String getDepartmentName();
    String getDepartmentHeadName();
}
