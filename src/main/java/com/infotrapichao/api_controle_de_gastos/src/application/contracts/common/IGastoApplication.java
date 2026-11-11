package com.infotrapichao.api_controle_de_gastos.src.application.contracts.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.TotaisMensaisResponse;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.math.BigDecimal;

public interface IGastoApplication {

    Gasto findById(Integer id);
    Gasto create(Gasto gasto);
    Gasto update(Gasto gasto);
    List<Gasto> findAll();
    List<Gasto> findAllByFilter(GastoDTO filter);
    Page<Gasto> findAllByFilterPageable(GastoDTO filter, int page, int size);
    Page<Gasto> findAllByFilter(GastoDTO filter, int page, int size);
    BigDecimal sumValorByFilter(GastoDTO filter);
    TotaisMensaisResponse findTotaisPorMes(GastoDTO filter, Integer ano);
}
