package com.example.demo.repository;

import com.example.demo.entity.DepartmentDetail;
import com.example.demo.entity.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentHeadRepository extends JpaRepository<DepartmentHead, String> {
    Optional<DepartmentHead> findByDepartmentId(String departmentId);
}
