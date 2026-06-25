package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaDePagamentoRepository extends JpaRepository<AgendaDePagamento, Integer> {
    List<AgendaDePagamento> findAll(Specification<AgendaDePagamento> agendamentoSpecification);
}
