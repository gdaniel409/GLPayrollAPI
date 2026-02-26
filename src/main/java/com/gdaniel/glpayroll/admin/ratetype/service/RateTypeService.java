package com.gdaniel.glpayroll.admin.ratetype.service;

import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.ratetype.dto.RateTypeDto;
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

    public Iterable<RateTypeDto> findAllRateTypes() {

        return rateTypeRepository.findAll().stream()
                .map(rateType -> convertToDto(rateType))
                .toList();

    }

    public void deleteById(Long rateTypeID) {
        rateTypeRepository.deleteById(rateTypeID);
    }

    public RateTypeDto create(RateTypeDto rateType) {
        return convertToDto(rateTypeRepository.save(convertToEntity(rateType)));
    }

    public RateTypeDto update(RateTypeDto rateType) {
        return convertToDto(rateTypeRepository.save(convertToEntity(rateType)));
    }

    public RateTypeDto convertToDto(RateTypeEntity rateType) {
        return new RateTypeDto(rateType);
    }

    public RateTypeEntity convertToEntity(RateTypeDto rateTypeDto) {
        return new RateTypeEntity(rateTypeDto);
    }

}
