package com.gdaniel.glpayroll.admin.role.service;

import org.springframework.stereotype.Service;
import com.gdaniel.glpayroll.admin.role.dto.RoleDto;
import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;
import com.gdaniel.glpayroll.admin.role.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public Iterable<RoleDto> findAllRolesForUser(String userName) {

        var roleList = new ArrayList<RoleEntity>(roleRepository.findAllRolesByUserName(userName));

        return roleList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public Iterable<RoleDto> findAllRoles() {

        Iterable<RoleEntity> roleList = roleRepository.findAllRoles();

        return ((Collection<RoleEntity>) roleList)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public RoleDto createRole(RoleDto dto) {
        RoleEntity roleEntity = convertToEntity(dto);
        roleEntity.setRoleId(null);
        return convertToDto(roleRepository.save(roleEntity));
    }

    public RoleDto updateRole(RoleDto roleDto) {
        RoleEntity existingRoleEntity = findEntityOrThrow(roleDto.getId());

        existingRoleEntity.setRoleName(roleDto.getRoleName());
        return convertToDto(roleRepository.save(existingRoleEntity));
    }

    public void deleteRole(long id) {
        RoleEntity existingRoleEntity = findEntityOrThrow(id);
        roleRepository.delete(existingRoleEntity);
    }

    private RoleEntity findEntityOrThrow(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    public RoleDto convertToDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }

    public RoleEntity convertToEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getId());
        entity.setRoleName(dto.getRoleName());
        return entity;
    }

}
