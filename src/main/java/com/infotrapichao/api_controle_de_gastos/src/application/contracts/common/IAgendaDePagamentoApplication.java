package com.infotrapichao.api_controle_de_gastos.src.application.contracts.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;

import java.util.List;

public interface IAgendaDePagamentoApplication {

    AgendaDePagamento findById(Integer id);
    AgendaDePagamento create(AgendaDePagamento  agendaDePagamento);
    AgendaDePagamento update(AgendaDePagamento agendaDePagamento);
    List<AgendaDePagamento> findAll();
    List<AgendaDePagamento> findAllByFilter(AgendaDePagamentoDTO filter);
}
