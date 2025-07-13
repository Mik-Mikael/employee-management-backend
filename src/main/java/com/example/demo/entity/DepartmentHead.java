package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString(), etc.
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Entity // Marks this class as a JPA entity
@Table(name = "department_head") // Specifies the table name in the database
public class DepartmentHead {
    @Id
    private String departmentId;

    private String employeeId;
}
