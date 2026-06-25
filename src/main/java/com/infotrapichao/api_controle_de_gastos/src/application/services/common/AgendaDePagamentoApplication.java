package com.infotrapichao.api_controle_de_gastos.src.application.services.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IAgendaDePagamentoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common.IAgendaDePagamentoService;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common.IGastoService;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDePagamentoApplication implements IAgendaDePagamentoApplication {

    private final IAgendaDePagamentoService _agendaDePagamentoService;

    public AgendaDePagamentoApplication(IAgendaDePagamentoService agendaDePagamentoService) {
        _agendaDePagamentoService = agendaDePagamentoService;
    }


    @Override
    public AgendaDePagamento findById(Integer id) {
        return _agendaDePagamentoService.findById(id);
    }

    @Override
    public AgendaDePagamento create(AgendaDePagamento agendaDePagamento) {
        return _agendaDePagamentoService.create(agendaDePagamento);
    }

    @Override
    public AgendaDePagamento update(AgendaDePagamento agendaDePagamento) {
        return _agendaDePagamentoService.update(agendaDePagamento);
    }

    @Override
    public List<AgendaDePagamento> findAll() {
        return _agendaDePagamentoService.findAll();
    }

    @Override
    public List<AgendaDePagamento> findAllByFilter(AgendaDePagamentoDTO filter) {
        return _agendaDePagamentoService.findAllByFilter(filter);
    }
}
