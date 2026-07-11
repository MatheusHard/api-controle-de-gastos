package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request;

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
public class AgendaDePagamentoRequestDTO {

    private Integer id;
    private Integer userId;
    private Boolean deletado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

}
