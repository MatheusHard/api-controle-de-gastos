package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO {
    private String login;
    private String token;
    private UserResponseDTO user;
}
