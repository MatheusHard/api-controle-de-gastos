package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.request.created;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Data de criação é obrigatória.")
    private LocalDateTime createdAt;
    @NotNull(message = "Data de atualização é obrigatória.")
    private LocalDateTime updatedAt;
    @NotNull(message = "Data de vencimento é obrigatória.")
    private LocalDateTime vencimento;
    @NotNull(message = "A descrição é obrigatório.")
    private String descricao;
    @NotNull(message = "Usuário é obrigatório.")
    private Integer userId;
    private Boolean deletado = false;
    @NotNull(message = "valor é obrigatório.")
    private BigDecimal valor;
    @NotNull(message = "Agenda de Pagamento é obrigatória.")
    private Integer agendaDePagamentoId;
    @NotNull(message = "Status do Pagamento é obrigatório.")
    private StatusPagamentoEnum statusPagamento;
    private Boolean pago = false;
    private String photoName;
    private String imagemBase64;
}
