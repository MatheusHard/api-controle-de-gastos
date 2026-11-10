package com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository  extends JpaRepository<Gasto, Integer> {
    List<Gasto> findAll(Specification<Gasto> agendamentoSpecification);
    Page<Gasto> findAll(Specification<Gasto> specification, Pageable pageable);

    @Query("SELECT COALESCE(SUM(g.valor), 0) FROM Gasto g " +
            "WHERE (:usuarioId IS NULL OR g.user.id = :usuarioId) " +
            "AND (:deletado IS NULL OR g.deletado = :deletado) " +
            "AND (:id IS NULL OR g.id = :id) " +
            "AND (:createdAt IS NULL OR g.createdAt = :createdAt) " +
            "AND (:dataInicial IS NULL OR g.updatedAt >= :dataInicial) " +
            "AND (:dataFinal IS NULL OR g.updatedAt <= :dataFinal) " +
            "AND (:vencimentoInicio IS NULL OR g.vencimento >= :vencimentoInicio) " +
            "AND (:vencimentoFim IS NULL OR g.vencimento <= :vencimentoFim) " +
            "AND (:pago IS NULL OR g.pago = :pago) " +
            "AND (:agendaId IS NULL OR g.agendaDePagamento.id = :agendaId) " +
            "AND (:statusPagamento IS NULL OR g.statusPagamento = :statusPagamento) " +
            "AND (:descricao IS NULL OR LOWER(g.descricao) LIKE LOWER(:descricao))")
    java.math.BigDecimal sumValorByFilters(
            @Param("usuarioId") Integer usuarioId,
            @Param("deletado") Boolean deletado,
            @Param("id") Integer id,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("dataInicial") java.time.LocalDateTime dataInicial,
            @Param("dataFinal") java.time.LocalDateTime dataFinal,
            @Param("vencimentoInicio") java.time.LocalDateTime vencimentoInicio,
            @Param("vencimentoFim") java.time.LocalDateTime vencimentoFim,
            @Param("pago") Boolean pago,
            @Param("agendaId") Integer agendaId,
            @Param("statusPagamento") com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum statusPagamento,
            @Param("descricao") String descricao);

    @Query("SELECT new com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.GastosMensaisDTO(" +
            "MONTH(g.createdAt), SUM(g.valor)) " +
            "FROM Gasto g " +
            "WHERE g.deletado = false " +
            "AND (:usuarioId IS NULL OR g.user.id = :usuarioId) " +
            "GROUP BY MONTH(g.createdAt) " +
            "ORDER BY MONTH(g.createdAt)")
    List<GastosMensaisDTO> findTotaisPorMes(@Param("usuarioId") Integer usuarioId);


}
