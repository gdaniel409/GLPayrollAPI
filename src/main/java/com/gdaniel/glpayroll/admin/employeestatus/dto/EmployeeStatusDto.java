package com.gdaniel.glpayroll.admin.employeestatus.dto;

import com.gdaniel.glpayroll.admin.employeestatus.entity.EmployeeStatusEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeStatusDto {

    public EmployeeStatusDto() {
    }

    public EmployeeStatusDto(EmployeeStatusEntity entity) {
        this.id = entity.getStatusId();
        this.status = entity.getStatus();
    }

    private long id;
    private String status;

}
