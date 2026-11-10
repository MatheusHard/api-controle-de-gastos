package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public record GastosPaginadosResponse(
        List<GastoResumoResponse> content,
        int number,
        int size,
        long totalElements,
        int totalPages,
        BigDecimal totalValor
) {
    public static GastosPaginadosResponse from(Page<Gasto> page, BigDecimal totalValor) {
        return new GastosPaginadosResponse(
                page.getContent().stream().map(GastoResumoResponse::from).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                totalValor
        );
    }
}
