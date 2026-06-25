package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GastoSpecification {

    public static Specification<Gasto> withFiltersDTO(GastoDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            /// Id
            if (filtro.getId() != null && filtro.getId() != 0) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            /// User Id:
            if (filtro.getUser() != null && filtro.getUser().getId() != null && filtro.getUser().getId() != 0) {
                predicates.add(cb.equal(root.get("user").get("id"), filtro.getUser().getId()));
            }
            /// CreatedAt
            if (filtro.getCreatedAt() != null) {
                predicates.add(cb.equal(root.get("createdAt"), filtro.getCreatedAt()));
            }
            /// Deletado
            if (filtro.getDeletado() != null) {
                predicates.add(cb.equal(root.get("deletado"), filtro.getDeletado()));
            }
            /// UpdatedAt (between)
            if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
                LocalDateTime inicio = filtro.getDataInicial().atStartOfDay();
                LocalDateTime fim = filtro.getDataFinal().atTime(LocalTime.MAX); // 23:59:59.999...
                predicates.add(cb.between(root.get("updatedAt"), inicio, fim));
            } else if (filtro.getDataInicial() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("updatedAt"), filtro.getDataInicial().atStartOfDay()));
            } else if (filtro.getDataFinal() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("updatedAt"), filtro.getDataFinal().atTime(LocalTime.MAX)));
            }
            /// Vencimento
            if (filtro.getVencimento() != null) {
                LocalDateTime inicioDia = filtro.getVencimento().toLocalDate().atStartOfDay();
                LocalDateTime fimDia = filtro.getVencimento().toLocalDate().atTime(LocalTime.MAX);
                predicates.add(cb.between(root.get("vencimento"), inicioDia, fimDia));
            }
            /// Pago
            if (filtro.getPago() != null) {
                predicates.add(cb.equal(root.get("pago"), filtro.getPago()));
            }
            /// AgendaDePagamento Id:
            if (filtro.getAgendaDePagamento() != null && filtro.getAgendaDePagamento().getId() != null && filtro.getAgendaDePagamento().getId() != 0) {
                predicates.add(cb.equal(root.get("agendaDePagamento").get("id"), filtro.getAgendaDePagamento().getId()));
            }
            /// Ordenação por updatedAt DESC
            assert query != null;
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
