package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoCreatedRequestDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime vencimento;
    private String descricao;
    private Integer userId;
    private Boolean deletado = false;
    private BigDecimal valor;
    private Integer agendaDePagamentoId;
    private StatusPagamentoEnum statusPagamento;
    private Boolean pago = false;
    private String photoName;
    private String imagemBase64;
}
