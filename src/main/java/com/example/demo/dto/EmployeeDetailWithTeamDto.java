package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDetailWithTeamDto {
    private String employeeId;
    private String name;
    private String mobileNo;
    private LocalDate startDate;
    private String departmentName;
    private String departmentHeadName;
    private List<TeamMemberDto> teamMembers;

    // Constructors
    public EmployeeDetailWithTeamDto() {
    }

    public EmployeeDetailWithTeamDto(String name, LocalDate startDate, String employeeId, String mobileNo,
                                   String departmentHeadName, String departmentName, List<TeamMemberDto> teamMembers) {
        this.name = name;
        this.startDate = startDate;
        this.employeeId = employeeId;
        this.mobileNo = mobileNo;
        this.departmentHeadName = departmentHeadName;
        this.departmentName = departmentName;
        this.teamMembers = teamMembers;
    }

    // Getters
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getDepartmentHeadName() {
        return departmentHeadName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<TeamMemberDto> getTeamMembers() {
        return teamMembers;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setDepartmentHeadName(String departmentHeadName) {
        this.departmentHeadName = departmentHeadName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setTeamMembers(List<TeamMemberDto> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
