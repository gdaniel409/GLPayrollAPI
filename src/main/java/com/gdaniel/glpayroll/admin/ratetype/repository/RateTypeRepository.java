package com.gdaniel.glpayroll.admin.ratetype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gdaniel.glpayroll.admin.ratetype.entity.RateTypeEntity;

@Repository
public interface RateTypeRepository extends JpaRepository<RateTypeEntity, Integer> {

    @Query("SELECT r FROM RateTypeEntity r WHERE r.id = ?1")
    RateTypeEntity findById(long id);

}
