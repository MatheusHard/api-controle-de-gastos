package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.updated.security;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedRequestDTO {

    @NotNull(message = "Id é obrigatório.")
    private Integer id;
    @NotNull(message = "Data de criação é obrigatória.")
    private LocalDateTime createdAt;
    @NotNull(message = "Data de atualização é obrigatória.")
    private LocalDateTime updatedAt;
    @NotNull(message = "Usuário é obrigatório.")
    private String username;
    @NotNull(message = "Email é obrigatório.")
    private String email;
    private String password;
    @NotNull(message = "Perfil(s) de acesso(s) é(são) obrigatório(s).")
    private List<String> roles;
}