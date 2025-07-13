package com.example.demo.repository;

import com.example.demo.entity.DepartmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDetailRepository extends JpaRepository<DepartmentDetail, String> {
    List<DepartmentDetail> findByDepartmentId(String departmentId);
}
