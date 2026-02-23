package com.gdaniel.glpayroll.admin.user.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;
import com.gdaniel.glpayroll.admin.user.dto.UserDto;
import com.gdaniel.glpayroll.admin.user.entity.UserEntity;
import com.gdaniel.glpayroll.admin.user.repository.UserRepository;
import com.gdaniel.glpayroll.exception.BadRequestException;
import com.gdaniel.glpayroll.exception.NotFoundException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repo;
    private final ModelMapper mapper;

    public UserEntity findByUserName(String userName) {
        return repo.findByUserName(userName);
    }

    public UserEntity findUsersWithRolesByUserName(String userName) {
        return repo.findUsersWithRolesByUserName(userName);
    }

    public List<RoleEntity> findRolesByUserName(String userName) {
        return repo.findRolesByUserName(userName);
    }

    public List<UserDto> findAllUsers() {
        var userEntityList = new ArrayList<>(repo.findAll());

        return userEntityList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto findUserById(final Long id) {
        var user = repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id " + id + " was not found"));

        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto, String password)
            throws NoSuchAlgorithmException {
        var user = convertToEntity(userDto);

        if (password.isBlank())
            throw new IllegalArgumentException(
                    "Password is required");

        var existsEmail = repo.selectExistsUserName(user.getUsername());
        if (Boolean.TRUE.equals(existsEmail))
            throw new BadRequestException(
                    "Username " + user.getUsername() + " taken");

        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(password, salt);

        String hexString = HexFormat.of().formatHex(hashedPassword);
        String saltString = HexFormat.of().formatHex(salt);

        user.setStoredSalt(saltString);
        user.setStoredHash(hexString);

        repo.save(user);

        return convertToDto(user);
    }

    public void updateUser(Long id, UserDto userDto, String password)
            throws NoSuchAlgorithmException {

        var user = findOrThrow(id);
        var userParam = convertToEntity(userDto);

        user.setUserName(userParam.getUsername());

        if (!password.isBlank()) {

            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);

            String hexString = HexFormat.of().formatHex(hashedPassword);
            String saltString = HexFormat.of().formatHex(salt);

            user.setStoredSalt(saltString);
            user.setStoredHash(hexString);
        }

        repo.save(user);

    }

    public void removeUserById(Long id) {
        findOrThrow(id);
        repo.deleteById(id);
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);

        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    private UserEntity findOrThrow(final Long id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id " + id + " was not found"));
    }

    private UserDto convertToDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }
}
