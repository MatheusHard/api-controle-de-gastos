package com.infotrapichao.api_controle_de_gastos.src.application.services.common;

import com.infotrapichao.api_controle_de_gastos.src.application.contracts.common.IGastoApplication;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.TotaisMensaisResponse;
import com.infotrapichao.api_controle_de_gastos.src.domain.contracts.services.common.IGastoService;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GastoApplication implements IGastoApplication {

    private final IGastoService _gastoService;

    public GastoApplication(IGastoService gastoService) {
        this._gastoService = gastoService;
    }

    @Override
    public Gasto findById(Integer id) {
        return _gastoService.findById(id);
    }

    @Override
    public Gasto create(Gasto gasto) {
        return _gastoService.create(gasto);
    }

    @Override
    public Gasto update(Gasto gasto) {
        return _gastoService.update(gasto);
    }

    @Override
    public List<Gasto> findAll() {
        return _gastoService.findAll();
    }

    @Override
    public List<Gasto> findAllByFilter(GastoDTO filter) {
        return _gastoService.findAllByFilter(filter);
    }

    @Override
    public Page<Gasto> findAllByFilter(GastoDTO filter, int page, int size) {
        return _gastoService.findAllByFilter(filter, page, size);
    }

    @Override
    public Page<Gasto> findAllByFilterPageable(GastoDTO filter, int page, int size) {
        return _gastoService.findAllByFilterPageable(filter, page, size);
    }

    @Override
    public BigDecimal sumValorByFilter(GastoDTO filter) {
        return _gastoService.sumValorByFilter(filter);
    }

    @Override
    public TotaisMensaisResponse findTotaisPorMes(GastoDTO filter, Integer ano) {

        List<GastosMensaisDTO> lista = _gastoService.findTotaisPorMes(filter, ano);
        BigDecimal somaTotal = lista.stream()
                .map(GastosMensaisDTO::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TotaisMensaisResponse(lista, somaTotal);

    }
}
