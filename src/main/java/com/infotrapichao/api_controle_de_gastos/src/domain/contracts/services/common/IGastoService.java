package com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;

import java.util.List;

public interface IGastoService {
    Gasto findById(Integer id);
    Gasto create(Gasto gasto);
    Gasto update(Gasto gasto);
    List<Gasto> findAll();
    List<Gasto> findAllByFilter(GastoDTO filter);
    List<GastosMensaisDTO> findTotaisPorMes(GastoDTO filter);

}
