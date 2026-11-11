package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.get.UserResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AgendaDePagamentoResponseDTO(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserResponseDTO user,
        Boolean deletado,
        LocalDate dataInicial,
        LocalDate dataFinal
) {
}
