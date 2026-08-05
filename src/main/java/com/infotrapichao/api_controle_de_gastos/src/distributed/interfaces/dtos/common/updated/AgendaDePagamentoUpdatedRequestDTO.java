package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.updated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDePagamentoUpdatedRequestDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
    private Boolean deletado;

}