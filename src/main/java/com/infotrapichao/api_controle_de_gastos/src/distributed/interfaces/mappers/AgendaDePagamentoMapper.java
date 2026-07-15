package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created.AgendaDePagamentoCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.AgendaDePagamentoRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.updated.AgendaDePagamentoUpdatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;

import java.util.ArrayList;
import java.util.List;

public final class AgendaDePagamentoMapper {

    private AgendaDePagamentoMapper() {
    }

    // Entity -> DTO
    public static AgendaDePagamentoDTO toDto(AgendaDePagamento entity) {
        if (entity == null) return null;

        return new AgendaDePagamentoDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getUser(),
                entity.getDeletado(),
                entity.getGastos(),
                null,
                null
        );
    }

    // RequestDTO -> DTO
    public static AgendaDePagamentoDTO toDto(AgendaDePagamentoRequestDTO request) {
        if (request == null) return null;

        return new AgendaDePagamentoDTO(
                request.getId(),
                request.getCreatedAt(),
                request.getUpdatedAt(),
                createUser(request.getUserId()),
                request.getDeletado(),
                null,
                request.getDataInicial(),
                request.getDataFinal()
        );
    }

    // DTO -> Entity
    public static AgendaDePagamento toEntity(AgendaDePagamentoDTO dto) {
        if (dto == null) return null;

        return new AgendaDePagamento(
                dto.getId(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getUser(),
                dto.getDeletado(),
                dto.getGastos()
        );
    }

    // CreateRequestDTO -> Entity
    public static AgendaDePagamento toEntity(AgendaDePagamentoCreatedRequestDTO request) {
        if (request == null) return null;

        return new AgendaDePagamento(
                null,
                request.getCreatedAt(),
                request.getUpdatedAt(),
                createUser(request.getUserId()),
                request.getDeletado(),
                null
        );
    }

    // UpdatedRequestDTO -> Entity
    public static AgendaDePagamento toEntity(AgendaDePagamentoUpdatedRequestDTO request) {
        if (request == null) return null;

        return new AgendaDePagamento(
                request.getId(),
                request.getCreatedAt(),
                request.getUpdatedAt(),
                createUser(request.getUserId()),
                request.getDeletado(),
                null
        );
    }

    public static List<AgendaDePagamentoDTO> toDtoList(List<AgendaDePagamento> entities) {
        return entities.stream()
                .map(AgendaDePagamentoMapper::toDto)
                .peek(dto -> {
                    if (dto.getUser() != null) {
                        dto.getUser().setPassword(null);
                        dto.getUser().setRoles(new ArrayList<>());
                        dto.getUser().setAgendaDePagamentos(new ArrayList<>());
                    }
                })
                .toList();
    }

    public static List<AgendaDePagamento> toEntityList(List<AgendaDePagamentoDTO> dtos) {
        return dtos.stream()
                .map(AgendaDePagamentoMapper::toEntity)
                .toList();
    }

    private static User createUser(Integer userId) {
        if (userId == null) {
            return null;
        }

        User user = new User();
        user.setId(userId);
        return user;
    }
}