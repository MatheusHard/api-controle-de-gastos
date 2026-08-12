package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response;

public record EmailResponseDTO(
        boolean success,
        String message
) {}