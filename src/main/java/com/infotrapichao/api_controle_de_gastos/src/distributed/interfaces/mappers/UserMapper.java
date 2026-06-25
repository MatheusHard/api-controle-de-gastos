package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;

import java.util.List;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getCreatedAt(), user.getUpdatedAt(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }

    public static User toUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getCreatedAt(), userDTO.getUpdatedAt(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), null, userDTO.getRoles());
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream().map(user -> {
                                  UserDTO dto =  UserMapper.toUserDTO(user);
                                  dto.setPassword(null);
                                  return dto;
                              }).toList();
                           }

    public static List<User> toUserList(List<UserDTO> usersDtos) {
        return usersDtos.stream().map(UserMapper::toUser).toList();
    }
}