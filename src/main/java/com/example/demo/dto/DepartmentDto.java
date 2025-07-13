package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
@Builder // Provides a builder pattern for object creation, useful for complex objects
public class DepartmentDto {
    private String departmentId;
    private String departmentName;
    private String orgCode;        // parent reference
    private String parentId;       // for building tree
    private String departmentHead;

    // --- Constructor ---
//    public DepartmentDto() {
//        // Default constructor
//    }
//
//    public DepartmentDto(String departmentId, String departmentName, String orgCode, String parentId, String departmentHead) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.orgCode = orgCode;
//        this.parentId = parentId;
//        this.departmentHead = departmentHead;
//    }
//
//    // --- Getters ---
//    public String getDepartmentId() {
//        return departmentId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public String getOrgCode() {
//        return orgCode;
//    }
//
//    public String getParentId() {
//        return parentId;
//    }
//
//    public String getDepartmentHead() {
//        return departmentHead;
//    }
//
//    // --- Setters ---
//    public void setDepartmentId(String departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public void setOrgCode(String orgCode) {
//        this.orgCode = orgCode;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }
//
//    public void setDepartmentHead(String departmentHead) {
//        this.departmentHead = departmentHead;
//    }
}
