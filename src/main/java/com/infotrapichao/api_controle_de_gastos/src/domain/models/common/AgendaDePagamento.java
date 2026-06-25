package com.infotrapichao.api_controle_de_gastos.src.domain.models.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AgendaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-agendadepagamentos")
    private User user;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean deletado;

    @OneToMany(mappedBy = "agendaDePagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "agendadepagamento-gastos")
    private List<Gasto> gastos;

}
