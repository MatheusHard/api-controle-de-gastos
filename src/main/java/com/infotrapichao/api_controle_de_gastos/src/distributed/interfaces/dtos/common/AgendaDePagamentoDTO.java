package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDePagamentoDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User user;
    private Boolean deletado;
    private List<Gasto> gastos;

    ///Filters
    private LocalDate dataInicial;
    private LocalDate dataFinal;

}
