package com.gdaniel.glpayroll.user.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gdaniel.glpayroll.user.employee.entitiy.EmployeeEntity;

// The left join returns all employees and the document collection if any.  The document collection
// is counted and the count returned so as to give the number of documents for each employee.  This is used to determine if the delete button
// if there are no documents zero is returned.
// This pattern should be used for deductions, departments, etc.
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT e FROM EmployeeEntity e LEFT JOIN e.documents d")
    List<EmployeeEntity> findAllWithDocCount();

}
