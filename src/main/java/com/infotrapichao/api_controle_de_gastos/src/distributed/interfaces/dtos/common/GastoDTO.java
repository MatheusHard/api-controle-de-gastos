package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime vencimento;
    private String descricao;
    private User user;
    private Boolean deletado = false;

    ///Filters
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private BigDecimal valor;
    private AgendaDePagamento AgendaDePagamento;
    private StatusPagamentoEnum statusPagamento;
    private Boolean pago = false;
    private String photoName;
    private String imagemBase64;

}
