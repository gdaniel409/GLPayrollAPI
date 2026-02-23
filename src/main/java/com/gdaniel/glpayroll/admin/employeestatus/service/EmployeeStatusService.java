package com.gdaniel.glpayroll.admin.employeestatus.service;

import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.employeestatus.entity.EmployeeStatusEntity;
import com.gdaniel.glpayroll.admin.employeestatus.repository.EmployeeStatusRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeStatusService {

    private EmployeeStatusRepository employeeStatusRepository;

    public EmployeeStatusEntity findById(Long id) {

        return employeeStatusRepository.findById(id);
    }

}
