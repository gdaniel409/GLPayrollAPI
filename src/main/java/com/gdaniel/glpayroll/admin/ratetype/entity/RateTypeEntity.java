package com.gdaniel.glpayroll.admin.ratetype.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdaniel.glpayroll.admin.ratetype.dto.RateTypeDto;
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
@Table(name = "tbl50cbbfdb285741f4aaaab654824de37d")
public class RateTypeEntity {

    public RateTypeEntity() {

    }

    public RateTypeEntity(RateTypeDto rateTypeDTO) {

        this.rateTypeId = rateTypeDTO.getId();
        this.rateType = rateTypeDTO.getRateType();
    }

    @Id
    @Column(name = "col7c58f73208714685b70bc4ec07ef553e", nullable = false)
    private Long rateTypeId;

    @JsonManagedReference
    @OneToMany(mappedBy = "rateType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeEntity> employees = new HashSet<>();

    @Column(name = "col9ec67ee267465db198115f810e700b", nullable = false, length = 45)
    private String rateType;

}
