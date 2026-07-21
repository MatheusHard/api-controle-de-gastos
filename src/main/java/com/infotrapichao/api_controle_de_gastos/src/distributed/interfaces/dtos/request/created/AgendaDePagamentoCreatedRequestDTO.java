package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDePagamentoCreatedRequestDTO {

    @NotNull(message = "Data de criação é obrigatória.")
    private LocalDateTime createdAt;
    @NotNull(message = "Data de atualização é obrigatória.")
    private LocalDateTime updatedAt;
    @NotNull(message = "Usuário é obrigatório.")
    private Integer userId;
    private Boolean deletado;

}
