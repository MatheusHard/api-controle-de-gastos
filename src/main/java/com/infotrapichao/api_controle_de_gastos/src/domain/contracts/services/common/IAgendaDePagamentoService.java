package com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;

import java.util.List;

public interface IAgendaDePagamentoService {
    AgendaDePagamento findById(Integer id);

    AgendaDePagamento create(AgendaDePagamento agendaDePagamento);

    AgendaDePagamento update(AgendaDePagamento agendaDePagamento);

    List<AgendaDePagamento> findAll();

    List<AgendaDePagamento> findAllByFilter(AgendaDePagamentoDTO filter);
}
