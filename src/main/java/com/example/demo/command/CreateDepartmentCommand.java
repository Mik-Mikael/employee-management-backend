package com.example.demo.command;

import lombok.Data;
import java.util.List;

@Data
public class CreateDepartmentCommand {

    private String departmentId;
    private String departmentName;
    private String orgCode;

    private String headEmployeeId; // For DepartmentHead

    private List<String> employeeIds; // For DepartmentDetail
}
