package com.infotrapichao.api_controle_de_gastos.src.domain.models.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;

    @Column(name = "vencimento", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime vencimento;

    @Column(length = 50, nullable = false)
    private String descricao;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean deletado = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(precision = 19, scale = 2) // 19 dígitos no total, 2 após a vírgula
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "agendadepagamento_id")
    @JsonBackReference(value = "agendadepagamento-gastos")
    private AgendaDePagamento agendaDePagamento;

    @Column
    @Comment("0 = Não pago, 1 = Vencido, 2 = Pago")
    private StatusPagamentoEnum statusPagamento;

    @Column(columnDefinition = "bit(1) default 0")
    private boolean pago = false;

    private String photoName;

    private String imagemBase64;

}
