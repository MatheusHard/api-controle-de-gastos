package com.infotrapichao.api_controle_de_gastos.src.domain.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common.IGastoService;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common.GastoRepository;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification.GastoSpecification;

import io.micrometer.common.lang.NonNull;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GastoService implements IGastoService {

    private final GastoRepository _gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this._gastoRepository = gastoRepository;
    }

    @Override
    public Gasto findById(Integer id) {
        return _gastoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Gasto create(@NonNull Gasto gasto) {
        if (gasto.getId() != null && _gastoRepository.existsById(gasto.getId())) {
            throw new IllegalArgumentException("Gasto já cadastrado!!!");
        } else {
            return _gastoRepository.save(gasto);
        }
    }

    @Override
    public Gasto update(Gasto gasto) {
        if (gasto.getId() != null && !_gastoRepository.existsById(gasto.getId())) {
            throw new IllegalArgumentException("Gasto não encontrado!!!");
        }

        Gasto gastoExistente = _gastoRepository.findById(gasto.getId())
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado"));

        // Atualiza os dados simples
        gastoExistente.setDescricao(gasto.getDescricao());
        gastoExistente.setVencimento(gasto.getVencimento());
        gastoExistente.setDeletado(gasto.isDeletado());
        gastoExistente.setUpdatedAt(LocalDateTime.now());

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
        return _gastoRepository.save(gasto);

    }

    @Override
    public List<Gasto> findAll() {
        return _gastoRepository.findAll();
    }

    @Override
    public List<Gasto> findAllByFilter(GastoDTO filter) {
        return _gastoRepository.findAll(GastoSpecification.withFiltersDTO(filter));
    }

    @Override
    public List<GastosMensaisDTO> findTotaisPorMes(GastoDTO filter) {
        return _gastoRepository.findTotaisPorMes(filter.getUser().getId());
    }
}
