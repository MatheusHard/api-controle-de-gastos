package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IAgendaDePagamentoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.AgendaDePagamentoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.created.AgendaDePagamentoCreatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.AgendaDePagamentoRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.updated.AgendaDePagamentoUpdatedRequestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.AgendaDePagamentoMapper;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("agendasdepagamento")
public class AgendaDePagamentoController {
    private final IAgendaDePagamentoApplication _agendaDePagamentoApplication;

    public AgendaDePagamentoController(IAgendaDePagamentoApplication agendaDePagamentoApplication) {
        this._agendaDePagamentoApplication = agendaDePagamentoApplication;
    }

    @PostMapping
    public ResponseEntity<AgendaDePagamento> create(@Validated @RequestBody AgendaDePagamentoCreatedRequestDTO agendaDePagamentoDTO) {

        AgendaDePagamento agendaDePagamento = AgendaDePagamentoMapper.toEntity(agendaDePagamentoDTO);
        var agendamentoCreated = _agendaDePagamentoApplication.create(agendaDePagamento);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendamentoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(agendamentoCreated);
    }

    @PutMapping()
    public ResponseEntity<AgendaDePagamento> put(@RequestBody AgendaDePagamentoUpdatedRequestDTO agendaDePagamentoDTO) {

        AgendaDePagamento agendaDePagamento = AgendaDePagamentoMapper.toEntity(agendaDePagamentoDTO);
        var agendaDePagamentoUpdated = _agendaDePagamentoApplication.update(agendaDePagamento);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendaDePagamentoUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(agendaDePagamentoUpdated);
    }

    @GetMapping()
    public ResponseEntity<List<AgendaDePagamentoDTO>> findAll() {
        var lista = AgendaDePagamentoMapper.toDtoList(_agendaDePagamentoApplication.findAll());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDePagamento> findById(@PathVariable("id") Integer id) {
        var agendaDePagamento = _agendaDePagamentoApplication.findById(id);
        return ResponseEntity.ok(agendaDePagamento);
    }
    @GetMapping("/findOne")
    public ResponseEntity<AgendaDePagamentoDTO> findOne(@ModelAttribute AgendaDePagamentoRequestDTO filter) {

        AgendaDePagamentoDTO dto = AgendaDePagamentoMapper.toDto(filter);
        var agendasDePagamento = _agendaDePagamentoApplication.findAllByFilter(dto);
        AgendaDePagamento agendaDePagamento = null;
        if(!agendasDePagamento.isEmpty()){
            agendaDePagamento = agendasDePagamento.getFirst();
        }
        var fatura = agendaDePagamento != null ? AgendaDePagamentoMapper.toDto(agendaDePagamento): null;
        return ResponseEntity.ok(fatura);
    }
    @GetMapping("/filtrar")
    public ResponseEntity<List<AgendaDePagamentoDTO>> filtrar(@ModelAttribute AgendaDePagamentoRequestDTO filter) {

        AgendaDePagamentoDTO dto = AgendaDePagamentoMapper.toDto(filter);
        var agendasDePagamento = _agendaDePagamentoApplication.findAllByFilter(dto);
        var lista = AgendaDePagamentoMapper.toDtoList(agendasDePagamento);
        return ResponseEntity.ok(lista);
    }
}
