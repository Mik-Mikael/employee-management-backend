package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query(value = "WITH RECURSIVE dept_tree AS (" +
            " SELECT d.department_id, d.department_name, d.org_code, NULL::varchar AS parent_id, ed.employee_name AS department_head " +
            " FROM department d " +
            " LEFT JOIN department_head dh ON d.department_id = dh.department_id " +
            " LEFT JOIN employee_detail ed ON dh.employee_id = ed.employee_id " +
            " WHERE d.org_code IS NULL " +
            " UNION ALL " +
            " SELECT d.department_id, d.department_name, d.org_code, dt.department_id AS parent_id, ed.employee_name AS department_head " +
            " FROM department d " +
            " JOIN dept_tree dt ON d.org_code = dt.department_id " +
            " LEFT JOIN department_head dh ON d.department_id = dh.department_id " +
            " LEFT JOIN employee_detail ed ON dh.employee_id = ed.employee_id " +
            ") SELECT * FROM dept_tree", nativeQuery = true)
    List<Object[]> getAllDepartmentsRaw();
}
