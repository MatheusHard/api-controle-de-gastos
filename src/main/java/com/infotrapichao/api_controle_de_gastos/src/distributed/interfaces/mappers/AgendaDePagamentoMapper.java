package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;

import java.util.ArrayList;
import java.util.List;

public class AgendaDePagamentoMapper {

    public static AgendaDePagamentoDTO toAgendaDePagamentoDTO(AgendaDePagamento agendaDePagamento) {
        return new AgendaDePagamentoDTO(agendaDePagamento.getId(), agendaDePagamento.getCreatedAt(),
                agendaDePagamento.getUpdatedAt(),
                agendaDePagamento.getUser(), agendaDePagamento.getDeletado(), agendaDePagamento.getGastos(), null,
                null);
    }

    public static AgendaDePagamento toAgendaDePagamento(AgendaDePagamentoDTO agendaDePagamentoDTO) {
        return new AgendaDePagamento(agendaDePagamentoDTO.getId(), agendaDePagamentoDTO.getCreatedAt(),
                agendaDePagamentoDTO.getUpdatedAt(),
                agendaDePagamentoDTO.getUser(), agendaDePagamentoDTO.getDeletado(), agendaDePagamentoDTO.getGastos());
    }

    public static List<AgendaDePagamentoDTO> toAgendamentoDTOList(List<AgendaDePagamento> agendasDePagamento) {
        return agendasDePagamento.stream().map(a -> {
            AgendaDePagamentoDTO dto = AgendaDePagamentoMapper.toAgendaDePagamentoDTO(a);
            // Aqui pode mudar campo do objeto, caso queira
            dto.getUser().setPassword(null);
            dto.getUser().setRoles(new ArrayList<>());
            dto.getUser().setAgendaDePagamentos(new ArrayList<>());

            return dto;
        }).toList();
    }

    public static List<AgendaDePagamento> toAgendaDePagamentoList(List<AgendaDePagamentoDTO> agendasDePagamentoDtos) {
        return agendasDePagamentoDtos.stream().map(AgendaDePagamentoMapper::toAgendaDePagamento).toList();
    }
}
