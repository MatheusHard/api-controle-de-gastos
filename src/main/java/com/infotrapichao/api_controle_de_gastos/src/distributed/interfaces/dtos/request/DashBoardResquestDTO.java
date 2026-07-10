package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardResquestDTO {
    private Integer userId;
    private Boolean deletado = false;
}
