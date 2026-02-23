package com.gdaniel.glpayroll.admin.ratetype.dto;

import com.gdaniel.glpayroll.admin.ratetype.entity.RateTypeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateTypeDto {

    public RateTypeDto() {
    }

    public RateTypeDto(RateTypeEntity entity) {
        this.id = entity.getRateTypeId();
        this.rateType = entity.getRateType();
    }

    private long id;
    private String rateType;

}
