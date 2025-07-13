package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder; // Often useful for tree structures
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
@Builder // Provides a builder pattern for object creation, useful for complex objects
public class DepartmentTreeDto {
    private String departmentId;
    private String departmentName;
    private String departmentHead;

    @Builder.Default
    private List<DepartmentTreeDto> subDepartment = new ArrayList<>(); // Initialize here or in constructor/builder
}
