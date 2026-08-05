package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.created;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDePagamentoCreatedRequestDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
    private Boolean deletado;

}
