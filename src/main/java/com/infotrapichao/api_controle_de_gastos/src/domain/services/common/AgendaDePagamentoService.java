package com.infotrapichao.api_controle_de_gastos.src.domain.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common.IAgendaDePagamentoService;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common.AgendaDePagamentoRepository;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification.AgendaDePagamentoEspecification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgendaDePagamentoService implements IAgendaDePagamentoService {
    private final AgendaDePagamentoRepository _agendaDePagamentoRepository;

    public AgendaDePagamentoService(AgendaDePagamentoRepository agendaDePagamentoRepository) {
        _agendaDePagamentoRepository = agendaDePagamentoRepository;
    }

    @Override
    public AgendaDePagamento findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return _agendaDePagamentoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public AgendaDePagamento create(AgendaDePagamento agendaDePagamento) {
        if (agendaDePagamento.getId() != null && _agendaDePagamentoRepository.existsById(agendaDePagamento.getId())) {
            throw new IllegalArgumentException("Agendamento já cadastrado!!!");
        } else {
            return _agendaDePagamentoRepository.save(agendaDePagamento);
        }
    }

    @Override
    public AgendaDePagamento update(AgendaDePagamento agendaDePagamento) {

        AgendaDePagamento agendaDePagamentoExistente = _agendaDePagamentoRepository.findById(agendaDePagamento.getId())
                .orElseThrow(() -> new RuntimeException("AgendaDePagamento não encontrado"));

        // Atualiza os dados simples
        agendaDePagamentoExistente.setDeletado(agendaDePagamento.getDeletado());
        agendaDePagamentoExistente.setUpdatedAt(LocalDateTime.now());

        // ATUALIZA A LISTA DE AGENDAMENTOS sem quebrar a referência:
        /*
         * gastoExistente.getAgendamentos().clear();
         * if (cliente.getAgendamentos() != null) {
         * for (Agendamento ag : cliente.getAgendamentos()) {
         * ag.setCliente(clienteExistente); // importante manter a referência
         * }
         * clienteExistente.getAgendamentos().addAll(cliente.getAgendamentos());
         * }
         */
        return _agendaDePagamentoRepository.save(agendaDePagamento);

    }

    @Override
    public List<AgendaDePagamento> findAll() {
        return _agendaDePagamentoRepository.findAll();
    }

    @Override
    public List<AgendaDePagamento> findAllByFilter(AgendaDePagamentoDTO filter) {
        return _agendaDePagamentoRepository.findAll(AgendaDePagamentoEspecification.withFiltersDTO(filter));
    }
}
