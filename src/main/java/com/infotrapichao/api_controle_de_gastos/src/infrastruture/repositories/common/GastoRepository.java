package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository  extends JpaRepository<Gasto, Integer> {
    List<Gasto> findAll(Specification<Gasto> agendamentoSpecification);

    @Query("SELECT new com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard.GastosMensaisDTO(" +
            "MONTH(g.createdAt), SUM(g.valor)) " +
            "FROM Gasto g " +
            "WHERE g.deletado = false " +
            "AND (:usuarioId IS NULL OR g.user.id = :usuarioId) " +
            "GROUP BY MONTH(g.createdAt) " +
            "ORDER BY MONTH(g.createdAt)")
    List<GastosMensaisDTO> findTotaisPorMes(@Param("usuarioId") Integer usuarioId);


}