package com.gdaniel.glpayroll.admin.ratetype.service;

import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.ratetype.entity.RateTypeEntity;
import com.gdaniel.glpayroll.admin.ratetype.repository.RateTypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RateTypeService {

    private RateTypeRepository rateTypeRepository;

    public RateTypeEntity findById(Long rateTypeID) {
        return rateTypeRepository.findById(rateTypeID);
    }

}
