package com.gdaniel.glpayroll.admin.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gdaniel.glpayroll.admin.user.dto.UserDto;
import com.gdaniel.glpayroll.admin.user.service.UserService;

import jakarta.validation.Valid;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/users")
    public Iterable<UserDto> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/api/v1/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.removeUserById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {
        return userService.createUser(userDto, userDto.getPassword());
    }

    @PutMapping("/api/v1/users/{id}")
    public void putUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        userService.updateUser(id, userDto, userDto.getPassword());
    }
}
