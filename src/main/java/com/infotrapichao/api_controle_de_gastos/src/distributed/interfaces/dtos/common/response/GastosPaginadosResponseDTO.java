package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public record GastosPaginadosResponseDTO(
        List<GastoResumoResponseDTO> content,
        int number,
        int size,
        long totalElements,
        int totalPages,
        BigDecimal totalValor
) {
    public static GastosPaginadosResponseDTO from(Page<Gasto> page, BigDecimal totalValor) {
        return new GastosPaginadosResponseDTO(
                page.getContent().stream().map(GastoResumoResponseDTO::from).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                totalValor
        );
    }
}
