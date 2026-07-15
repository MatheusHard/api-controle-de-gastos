package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.TotaisMensaisResponse;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get.DashBoardResquestDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers.GastoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {

    private final IGastoApplication _gastoApplication;

    public DashBoardController(IGastoApplication gastoApplication) {
        this._gastoApplication = gastoApplication;
    }

    @GetMapping("/totais-mensais")
    public ResponseEntity<TotaisMensaisResponse> buscar(@ModelAttribute DashBoardResquestDTO filter) {
        GastoDTO dto = GastoMapper.toGastoDTO(filter);
        var gastos = _gastoApplication.findTotaisPorMes(dto);
        return ResponseEntity.ok(gastos);
    }

}
