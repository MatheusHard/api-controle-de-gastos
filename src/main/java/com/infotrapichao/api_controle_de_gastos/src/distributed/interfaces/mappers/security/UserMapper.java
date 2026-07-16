package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.security;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created.security.UserCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.updated.security.UserUpdatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;

import java.time.LocalDateTime;
import java.util.List;

public final class UserMapper {

    private UserMapper() {}

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    public static User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return createUser(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRoles()
        );
    }

    public static User toUser(UserCreatedRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return createUser(
                null,
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRoles()
        );
    }

    public static User toUser(UserUpdatedRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return createUser(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRoles()
        );
    }

    private static User createUser(
            Integer id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String username,
            String email,
            String password,
            List<String> roles) {

        return new User(
                id,
                createdAt,
                updatedAt,
                username,
                email,
                password,
                null,
                roles
        );
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserDTO)
                .peek(dto -> dto.setPassword(null))
                .toList();
    }

    public static List<User> toUserList(List<UserDTO> dtos) {
        return dtos.stream()
                .map(UserMapper::toUser)
                .toList();
    }
}