package com.gdaniel.glpayroll.admin.employeestatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gdaniel.glpayroll.admin.employeestatus.entity.EmployeeStatusEntity;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatusEntity, Integer> {

    @Query("SELECT e FROM EmployeeStatusEntity e WHERE e.id = ?1")
    EmployeeStatusEntity findById(long id);

}
