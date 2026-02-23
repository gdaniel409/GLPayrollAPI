package com.gdaniel.glpayroll.admin.employeestatus.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdaniel.glpayroll.admin.employeestatus.dto.EmployeeStatusDto;
import com.gdaniel.glpayroll.user.employee.entitiy.EmployeeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tblc2d1b107a6214854b77794869e260c79")
public class EmployeeStatusEntity {

    public EmployeeStatusEntity() {

    }

    public EmployeeStatusEntity(EmployeeStatusDto employeeStatusDTO) {

        this.statusId = employeeStatusDTO.getId();
        this.status = employeeStatusDTO.getStatus();
    }

    @Id // Specifies the primary key
    @Column(name = "col17127604a30e483e808eb0de8dbaf599", nullable = false)
    private Long statusId;

    @JsonManagedReference
    @OneToMany(mappedBy = "employeeStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeEntity> employees = new HashSet<>();

    @Column(name = "col2d1b107a6214854b77794869e260c79", nullable = false, length = 45)
    private String status;

}
