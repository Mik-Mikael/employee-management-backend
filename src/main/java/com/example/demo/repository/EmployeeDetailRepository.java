package com.example.demo.repository;

import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.dto.EmployeeSearchWithTeamDto;
import com.example.demo.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, String> {

    @Query(
            value = "SELECT " +
                    "    ed.employee_id, " +
                    "    ed.employee_name AS name, " + // Alias 'name' for mapping to getName()
                    "    ed.mobile_no, " +
                    "    ed.start_date, " +
                    "    d.department_name, " +
                    "    hed.employee_name AS department_head_name " + // Alias 'department_head_name' for mapping
                    "FROM " +
                    "    public.employee_detail AS ed " +
                    "JOIN " +
                    "    public.department_detail AS dd ON ed.employee_id = dd.employee_id " +
                    "JOIN " +
                    "    public.department AS d ON dd.department_id = d.department_id " +
                    "LEFT JOIN " +
                    "    public.department_head AS dh ON d.department_id = dh.department_id " +
                    "LEFT JOIN " +
                    "    public.employee_detail AS hed ON dh.employee_id = hed.employee_id " +
                    "WHERE " +
                    "    ed.terminate_date IS NULL and d.department_id = :department_id",
            nativeQuery = true
    )
    List<EmployeeSearchDto> findActiveEmployeesWithDepartmentAndHead(@Param("department_id") String department_id);

    @Query(
            value = "SELECT " +
                    "    ed.employee_id, " +
                    "    ed.employee_name AS name, " +
                    "    ed.mobile_no, " +
                    "    ed.start_date, " +
                    "    d.department_name, " +
                    "    hed.employee_name AS department_head_name, " +
                    "    'MAIN_EMPLOYEE' AS record_type " + // Identifier for the main employee record
                    "FROM " +
                    "    public.employee_detail AS ed " +
                    "JOIN " +
                    "    public.department_detail AS dd ON ed.employee_id = dd.employee_id " +
                    "JOIN " +
                    "    public.department AS d ON dd.department_id = d.department_id " +
                    "LEFT JOIN " +
                    "    public.department_head AS dh ON d.department_id = dh.department_id " +
                    "LEFT JOIN " +
                    "    public.employee_detail AS hed ON dh.employee_id = hed.employee_id " +
                    "WHERE " +
                    "    ed.employee_id = :employeeId AND ed.terminate_date IS NULL " +
                    "UNION ALL " + // Combine results
                    "SELECT " +
                    "    team_ed.employee_id, " +
                    "    team_ed.employee_name AS name, " +
                    "    team_ed.mobile_no, " +
                    "    NULL AS start_date, " + // These columns are not relevant for team members in this specific output
                    "    NULL AS department_name, " +
                    "    NULL AS department_head_name, " +
                    "    'TEAM_MEMBER' AS record_type " + // Identifier for team member records
                    "FROM " +
                    "    public.employee_detail AS team_ed " +
                    "JOIN " +
                    "    public.department_detail AS team_dd ON team_ed.employee_id = team_dd.employee_id " +
                    "JOIN " +
                    "    public.department_head AS dh_head ON team_dd.department_id = dh_head.department_id " +
                    "WHERE " +
                    "    dh_head.employee_id = :employeeId " + // Link team members to the given employee_id if they are a head
                    "    AND team_ed.terminate_date IS NULL " +
                    "    AND team_ed.employee_id != :employeeId", // Exclude the head themselves from the team list
            nativeQuery = true
    )
    List<EmployeeSearchWithTeamDto> findEmployeeDetailsAndTeam(@Param("employeeId") String employeeId);

    @Query(value = """
            SELECT
                ed.employee_id AS employeeId,
                ed.employee_name AS name,
                ed.mobile_no AS mobileNo,
                ed.start_date AS startDate,
                d.department_name AS departmentName,
                head_ed.employee_name AS departmentHeadName
            FROM employee_detail ed
            LEFT JOIN department_detail dd ON ed.employee_id = dd.employee_id
            LEFT JOIN department d ON dd.department_id = d.department_id
            LEFT JOIN department_head dh ON d.department_id = dh.department_id
            LEFT JOIN employee_detail head_ed ON dh.employee_id = head_ed.employee_id
            WHERE ed.employee_id = :employeeId
            """, nativeQuery = true)
    Map<String, Object> findEmployeeMainInfo(@Param("employeeId") String employeeId);

    @Query(value = """
            SELECT
                ed.employee_id AS employeeId,
                ed.employee_name AS name,
                ed.mobile_no AS mobileNo
            FROM department_head dh
            JOIN department_detail dd ON dh.department_id = dd.department_id
            JOIN employee_detail ed ON dd.employee_id = ed.employee_id
            WHERE dh.employee_id = :employeeId
            AND ed.employee_id != :employeeId""", nativeQuery = true)
    List<Map<String, Object>> findTeamMembersIfHead(@Param("employeeId") String employeeId);
}
