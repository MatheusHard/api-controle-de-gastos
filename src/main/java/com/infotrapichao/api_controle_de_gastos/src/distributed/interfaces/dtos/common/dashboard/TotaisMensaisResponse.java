package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard;

import java.math.BigDecimal;
import java.util.List;

public record TotaisMensaisResponse(List<GastosMensaisDTO> totaisPorMes, BigDecimal somaTotal) {}
