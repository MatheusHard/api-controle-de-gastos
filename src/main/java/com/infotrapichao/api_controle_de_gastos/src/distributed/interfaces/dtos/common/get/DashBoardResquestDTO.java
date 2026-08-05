package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardResquestDTO {
    private Integer userId;
    private Boolean deletado = false;
}
