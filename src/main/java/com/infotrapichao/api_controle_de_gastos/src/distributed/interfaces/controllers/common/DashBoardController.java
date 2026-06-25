package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.controllers.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.TotaisMensaisResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {

    private final IGastoApplication _gastoApplication;

    public DashBoardController(IGastoApplication gastoApplication) {
        this._gastoApplication = gastoApplication;
    }

    @PostMapping("/totais-mensais")
    public ResponseEntity<TotaisMensaisResponse> buscar(@RequestBody GastoDTO filter) {
        var gastos = _gastoApplication.findTotaisPorMes(filter);
        return ResponseEntity.ok(gastos);
    }

}
