package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
