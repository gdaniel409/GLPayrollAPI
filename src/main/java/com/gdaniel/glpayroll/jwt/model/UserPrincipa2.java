package com.gdaniel.glpayroll.jwt.model;

import java.util.Set;

import com.gdaniel.glpayroll.admin.user.entity.UserEntity;

public class UserPrincipa2 extends UserPrincipal {

    public UserPrincipa2(UserEntity userEntity) {
        super(userEntity);

    }

    public Set<String> getRoles() {
        return this.userEntity.getRoles().stream().map(r -> r.getRoleName())
                .collect(java.util.stream.Collectors.toSet());
    }

}
