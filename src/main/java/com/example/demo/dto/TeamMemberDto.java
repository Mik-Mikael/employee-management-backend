package com.example.demo.dto;

public class TeamMemberDto {
    private String employeeId;
    private String name;
    private String mobileNo;

    // Constructors
    public TeamMemberDto() {
    }

    public TeamMemberDto(String employeeId, String name, String mobileNo) {
        this.employeeId = employeeId;
        this.name = name;
        this.mobileNo = mobileNo;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    // Setters
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}