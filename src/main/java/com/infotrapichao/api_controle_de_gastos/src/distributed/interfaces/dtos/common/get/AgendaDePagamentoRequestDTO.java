package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDePagamentoRequestDTO {

    private Integer id;
    private Integer userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deletado;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

}