package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String email;
    private String password;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}