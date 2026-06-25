package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.core.utils.Utils;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.clients.PhotoClient;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("gastos")
public class GastoController {

    private final IGastoApplication _gastoApplication;
    private final PhotoClient photoClient;

    public GastoController(IGastoApplication gastoApplication, PhotoClient photoClient) {
        this._gastoApplication = gastoApplication;
        this.photoClient = photoClient;
    }

    @PostMapping
    public ResponseEntity<Gasto> create(@Validated @RequestBody GastoDTO gastoDTO, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        Gasto gasto = GastoMapper.toGasto(gastoDTO);

        // Micro-serviço de imagens
        if (gasto.getPhotoName() != null && gasto.getImagemBase64() != null) {
            photoClient.upload(
                    gasto.getPhotoName(),
                    gasto.getImagemBase64(),
                    authorization);
        }

        gasto.setImagemBase64(null);
        var agendamentoCreated = _gastoApplication.create(gasto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendamentoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(agendamentoCreated);
    }

    @PutMapping()
    public ResponseEntity<Gasto> put(@RequestBody GastoDTO gastoDTO, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Gasto gasto = GastoMapper.toGasto(gastoDTO);
        // Micro-serviço de imagens
        if (gasto.getPhotoName() != null && gasto.getImagemBase64() != null) {
            photoClient.upload(
                    gasto.getPhotoName(),
                    gasto.getImagemBase64(),
                    authorization);
        }
        gasto.setImagemBase64(null);
        var gastoUpdated = _gastoApplication.update(gasto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(gastoUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(gastoUpdated);
    }

    @GetMapping()
    public ResponseEntity<List<GastoDTO>> findAll() {
        var lista = GastoMapper.toAgendamentoDTOList(_gastoApplication.findAll());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable("id") Integer id) {
        var gasto = _gastoApplication.findById(id);
        return ResponseEntity.ok(gasto);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<GastoDTO>> filtrar(@RequestBody GastoDTO filter) {
        var gastos = _gastoApplication.findAllByFilter(filter);
        var lista = GastoMapper.toAgendamentoDTOList(gastos);
        return ResponseEntity.ok(lista);
    }

}
