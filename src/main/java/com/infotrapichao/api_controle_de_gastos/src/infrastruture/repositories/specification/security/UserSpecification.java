package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.specification.security;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.get.UserDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> withFiltersDTO(UserDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Id
            if (filtro.getId() != null && filtro.getId() != 0) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            // UserName
            if (filtro.getUsername() != null && !filtro.getUsername().trim().isEmpty()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("username")),
                                "%" + filtro.getUsername().trim().toLowerCase() + "%"
                        )
                );
            }
            // Email
            if (filtro.getEmail() != null) {
                predicates.add(cb.equal(root.get("email"), filtro.getEmail()));
            }
            // CreatedAt
            if (filtro.getCreatedAt() != null) {
                predicates.add(cb.equal(root.get("createdAt"), filtro.getCreatedAt()));
            }
            // UpdatedAt (between)
            if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
                LocalDateTime inicio = filtro.getDataInicial().toLocalDate().atStartOfDay();
                LocalDateTime fim = filtro.getDataFinal().toLocalDate().atTime(LocalTime.MAX); // 23:59:59.999...
                predicates.add(cb.between(root.get("updatedAt"), inicio, fim));
            } else if (filtro.getDataInicial() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("updatedAt"), filtro.getDataInicial().toLocalDate().atStartOfDay()));
            } else if (filtro.getDataFinal() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("updatedAt"), filtro.getDataFinal().toLocalDate().atTime(LocalTime.MAX)));
            }

            // Ordenação por updatedAt DESC
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
