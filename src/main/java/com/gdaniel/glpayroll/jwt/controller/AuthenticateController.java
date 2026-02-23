package com.gdaniel.glpayroll.jwt.controller;

import lombok.AllArgsConstructor;

import java.security.NoSuchAlgorithmException;

import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gdaniel.glpayroll.admin.user.entity.UserEntity;
import com.gdaniel.glpayroll.jwt.model.AuthenticationRequest;
import com.gdaniel.glpayroll.jwt.model.AuthenticationResponse;
import com.gdaniel.glpayroll.jwt.service.ApplicationUserDetailsService;
import com.gdaniel.glpayroll.jwt.util.JwtUtil;
import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/auth")
class AuthenticateController {

    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest req) throws BadCredentialsException, NoSuchAlgorithmException {

        UserEntity user;

        try {
            user = userDetailsService.authenticate(req.getUsername(), req.getPassword());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        var userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        List<RoleEntity> roles = userDetailsService.loadUserRolesByUsername(user.getUsername());

        var jwt = jwtTokenUtil.generateToken(userDetails, roles.stream().map(RoleEntity::getRoleName).toList());

        return new AuthenticationResponse(jwt);
    }
}
