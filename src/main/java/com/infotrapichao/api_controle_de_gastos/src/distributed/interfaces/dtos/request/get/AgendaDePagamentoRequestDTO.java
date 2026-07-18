package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.get;

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
    private Boolean deletado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

}
