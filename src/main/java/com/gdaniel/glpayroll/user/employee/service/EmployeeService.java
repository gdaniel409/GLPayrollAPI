package com.gdaniel.glpayroll.user.employee.service;

import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.employeestatus.dto.EmployeeStatusDto;
import com.gdaniel.glpayroll.admin.employeestatus.entity.EmployeeStatusEntity;
import com.gdaniel.glpayroll.admin.employeestatus.service.EmployeeStatusService;
import com.gdaniel.glpayroll.admin.ratetype.dto.RateTypeDto;
import com.gdaniel.glpayroll.admin.ratetype.entity.RateTypeEntity;
import com.gdaniel.glpayroll.admin.ratetype.service.RateTypeService;
import com.gdaniel.glpayroll.user.employee.dto.EmployeeDto;
import com.gdaniel.glpayroll.user.employee.entitiy.EmployeeEntity;
import com.gdaniel.glpayroll.user.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final EmployeeStatusService employeeStatusService;
    private final RateTypeService rateTypeService;

    public Iterable<EmployeeDto> findAllWithDocCount(Pageable pageable) {

        int skip = pageable.getPageSize() * pageable.getPageNumber();

        return employeeRepository.findAllWithDocCount()
                .stream()
                .skip(skip).limit(pageable.getPageSize())

                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public EmployeeDto findById(long id) {
        return convertToDto(findEntityOrThrow(id));
    }

    public EmployeeDto createEmployee(EmployeeDto dto) {
        // Implement business logic related to employees here

        EmployeeEntity entity = convertToEntity(dto);
        entity.setEmployeeId(null);
        return convertToDto(employeeRepository.save(entity));
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        // Implement business logic related to employees here

        EmployeeEntity existingEmployeeEntity = findEntityOrThrow(employeeDto.getId());

        existingEmployeeEntity.setFirstName(employeeDto.getFirstName());
        existingEmployeeEntity.setLastName(employeeDto.getLastName());
        existingEmployeeEntity.setMiddleName(employeeDto.getMiddleName());
        existingEmployeeEntity.setDateHired(employeeDto.getDateHired());
        existingEmployeeEntity.setDateTerminated(employeeDto.getDateTerminated());
        existingEmployeeEntity.setSsn(employeeDto.getSsn());
        existingEmployeeEntity.setEmail(employeeDto.getEmail());
        existingEmployeeEntity.setTelephoneLandline(employeeDto.getTelephoneLandline());
        existingEmployeeEntity.setTelephoneCell(employeeDto.getTelephoneCell());
        existingEmployeeEntity.setRate(employeeDto.getRate());

        EmployeeStatusEntity statusEntity = employeeStatusService.findById(employeeDto.getEmployeeStatus().getId());
        existingEmployeeEntity.setEmployeeStatus(statusEntity);

        RateTypeEntity rateTypeEntity = rateTypeService.findById(employeeDto.getRateType().getId());
        existingEmployeeEntity.setRateType(rateTypeEntity);

        existingEmployeeEntity.setTitle(employeeDto.getTitle());
        existingEmployeeEntity.setCompanyId(employeeDto.getCompanyId());
        existingEmployeeEntity.setEmployeeNumber(employeeDto.getEmployeeNumber());

        return convertToDto(employeeRepository.save(existingEmployeeEntity));

    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeEntity findEntityOrThrow(final long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    public EmployeeDto convertToDto(EmployeeEntity entity) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(entity.getEmployeeId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setDateHired(entity.getDateHired());
        dto.setSsn(entity.getSsn());

        dto.setDateTerminated(entity.getDateTerminated());
        dto.setEmail(entity.getEmail());
        dto.setTelephoneLandline(entity.getTelephoneLandline());
        dto.setTelephoneCell(entity.getTelephoneCell());
        dto.setRate(entity.getRate());
        dto.setRateType(new RateTypeDto(entity.getRateType()));
        dto.setEmployeeStatus(new EmployeeStatusDto(entity.getEmployeeStatus()));
        dto.setTitle(entity.getTitle());
        dto.setCompanyId(entity.getCompanyId());
        dto.setEmployeeNumber(entity.getEmployeeNumber());
        dto.setDocumentCount(entity.getDocuments().size());

        return dto;

    }

    public EmployeeEntity convertToEntity(EmployeeDto dto) {

        EmployeeEntity entity = new EmployeeEntity();

        entity.setEmployeeId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setDateHired(dto.getDateHired());
        entity.setDateTerminated(dto.getDateTerminated());
        entity.setSsn(dto.getSsn());
        entity.setEmail(dto.getEmail());
        entity.setTelephoneLandline(dto.getTelephoneLandline());
        entity.setTelephoneCell(dto.getTelephoneCell());
        entity.setRate(dto.getRate());

        EmployeeStatusEntity statusEntity = employeeStatusService.findById(dto.getEmployeeStatus().getId());
        entity.setEmployeeStatus(statusEntity);

        RateTypeEntity rateTypeEntity = rateTypeService.findById(dto.getRateType().getId());
        entity.setRateType(rateTypeEntity);

        entity.setTitle(dto.getTitle());
        entity.setCompanyId(dto.getCompanyId());
        entity.setEmployeeNumber(dto.getEmployeeNumber());

        return entity;
    }

}
