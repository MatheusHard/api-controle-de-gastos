package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.response;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GastoResumoResponse(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime vencimento,
        String descricao,
        Integer userId,
        String username,
        Boolean deletado,
        BigDecimal valor,
        Integer agendaDePagamentoId,
        StatusPagamentoEnum statusPagamento,
        Boolean pago,
        String photoName
) {
    public static GastoResumoResponse from(Gasto gasto) {
        return new GastoResumoResponse(
                gasto.getId(),
                gasto.getCreatedAt(),
                gasto.getUpdatedAt(),
                gasto.getVencimento(),
                gasto.getDescricao(),
                gasto.getUser() == null ? null : gasto.getUser().getId(),
                gasto.getUser() == null ? null : gasto.getUser().getUsername(),
                gasto.isDeletado(),
                gasto.getValor(),
                gasto.getAgendaDePagamento() == null ? null : gasto.getAgendaDePagamento().getId(),
                gasto.getStatusPagamento(),
                gasto.isPago(),
                gasto.getPhotoName()
        );
    }
}
