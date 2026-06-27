package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common;



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
public class EmailDTO {

    private String descricao;
    private String nomeUsuario;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime vencimento;
    private String remetente;
    private String destinatario;
    private String assunto;
    private String corpo;
    private BigDecimal valor;

}
