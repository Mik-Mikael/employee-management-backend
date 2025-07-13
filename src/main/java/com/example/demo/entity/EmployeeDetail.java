package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Generates getters, setters, toString(), etc.
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Entity // Marks this class as a JPA entity
@Table(name = "employee_detail") // Specifies the table name in the database
public class EmployeeDetail {
    @Id
    private String employeeId;

    private String employeeName;

    private LocalDate birthdate;

    private String mobileNo;

    private String email;

    private String address;

    private LocalDate startDate;

    private LocalDate terminateDate;
}
