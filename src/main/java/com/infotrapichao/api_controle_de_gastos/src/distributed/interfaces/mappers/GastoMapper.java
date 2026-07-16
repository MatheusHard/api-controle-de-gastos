package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created.GastoCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.DashBoardResquestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.GastoRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.updated.GastoUpdatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;

import java.time.LocalDateTime;
import java.util.List;

public final class GastoMapper {

    private GastoMapper() {}

    public static GastoDTO toGastoDTO(Gasto gasto) {
        if (gasto == null) {
            return null;
        }

        return new GastoDTO(
                gasto.getId(),
                gasto.getCreatedAt(),
                gasto.getUpdatedAt(),
                gasto.getVencimento(),
                gasto.getDescricao(),
                gasto.getUser(),
                gasto.isDeletado(),
                null,
                null,
                gasto.getValor(),
                gasto.getAgendaDePagamento(),
                gasto.getStatusPagamento(),
                gasto.isPago(),
                gasto.getPhotoName(),
                gasto.getImagemBase64()
        );
    }

    public static GastoDTO toGastoDTO(GastoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return new GastoDTO(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getVencimento(),
                dto.getDescricao(),
                createUser(dto.getUserId()),
                dto.getDeletado(),
                dto.getDataInicial(),
                dto.getDataFinal(),
                dto.getValor(),
                createAgenda(dto.getAgendaDePagamentoId()),
                dto.getStatusPagamento(),
                dto.getPago(),
                null,
                null
        );
    }

    public static GastoDTO toGastoDTO(DashBoardResquestDTO dto) {
        if (dto == null) {
            return null;
        }

        return new GastoDTO(
                null,
                null,
                null,
                null,
                null,
                createUser(dto.getUserId()),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static Gasto toGasto(GastoDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Gasto(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getVencimento(),
                dto.getDescricao(),
                dto.getDeletado(),
                dto.getUser(),
                dto.getValor(),
                dto.getAgendaDePagamento(),
                dto.getStatusPagamento(),
                dto.getPago(),
                dto.getPhotoName(),
                dto.getImagemBase64()
        );
    }

    public static Gasto toGasto(GastoCreatedRequestDTO dto) {
        return toGasto(
                null,
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getVencimento(),
                dto.getDescricao(),
                dto.getDeletado(),
                dto.getUserId(),
                dto.getAgendaDePagamentoId(),
                dto.getValor(),
                dto.getStatusPagamento(),
                dto.getPago(),
                dto.getPhotoName(),
                dto.getImagemBase64()
        );
    }

    public static Gasto toGasto(GastoUpdatedRequestDTO dto) {
        return toGasto(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getVencimento(),
                dto.getDescricao(),
                dto.getDeletado(),
                dto.getUserId(),
                dto.getAgendaDePagamentoId(),
                dto.getValor(),
                dto.getStatusPagamento(),
                dto.getPago(),
                dto.getPhotoName(),
                dto.getImagemBase64()
        );
    }

    private static Gasto toGasto(
            Integer id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime vencimento,
            String descricao,
            Boolean deletado,
            Integer userId,
            Integer agendaId,
            java.math.BigDecimal valor,
            StatusPagamentoEnum statusPagamento,
            Boolean pago,
            String photoName,
            String imagemBase64) {

        return new Gasto(
                id,
                createdAt,
                updatedAt,
                vencimento,
                descricao,
                deletado,
                createUser(userId),
                valor,
                createAgenda(agendaId),
                statusPagamento,
                pago,
                photoName,
                imagemBase64
        );
    }

    private static User createUser(Integer id) {
        if (id == null) {
            return null;
        }

        User user = new User();
        user.setId(id);
        return user;
    }

    private static AgendaDePagamento createAgenda(Integer id) {
        if (id == null) {
            return null;
        }

        AgendaDePagamento agenda = new AgendaDePagamento();
        agenda.setId(id);
        return agenda;
    }

    public static List<GastoDTO> toGastoDTOList(List<Gasto> gastos) {
        return gastos.stream()
                .map(GastoMapper::toGastoDTO)
                .peek(dto -> {
                    if (dto.getUser() != null) {
                        dto.getUser().setPassword(null);
                    }
                })
                .toList();
    }

    public static List<Gasto> toGastoList(List<GastoDTO> dtos) {
        return dtos.stream()
                .map(GastoMapper::toGasto)
                .toList();
    }
}