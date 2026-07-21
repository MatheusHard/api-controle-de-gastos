package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotNull(message = "Usuário é obrigatório.")
    private String username;
    @NotNull(message = "Senha é obrigatória.")
    private String password;

}
