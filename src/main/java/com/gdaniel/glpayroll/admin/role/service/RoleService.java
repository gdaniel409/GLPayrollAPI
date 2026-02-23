package com.gdaniel.glpayroll.admin.role.service;

import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.role.dto.RoleDto;
import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;
import com.gdaniel.glpayroll.admin.role.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;
    private final ModelMapper mapper;

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public List<RoleDto> findAllRolesForUser(String userName) {

        var roleList = new ArrayList<RoleEntity>(roleRepository.findAllRolesByUserName(userName));

        return roleList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private RoleDto convertToDto(RoleEntity entity) {
        return mapper.map(entity, RoleDto.class);
    }

    private RoleEntity convertToEntity(RoleDto dto) {
        return mapper.map(dto, RoleEntity.class);
    }

}
