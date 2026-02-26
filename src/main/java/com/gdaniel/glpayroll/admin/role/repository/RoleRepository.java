package com.gdaniel.glpayroll.admin.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("SELECT r FROM RoleEntity r JOIN r.users u WHERE u.userName = :userName")
    public List<RoleEntity> findAllRolesByUserName(String userName);

    @Query("SELECT r FROM RoleEntity r")
    public Iterable<RoleEntity> findAllRoles();

}
