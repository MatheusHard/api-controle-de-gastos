package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GastoDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime vencimento;
    private String descricao;
    private User user;
    private Boolean deletado = false;
    ///Filters
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private BigDecimal valor;
    private AgendaDePagamento AgendaDePagamento;
    private StatusPagamentoEnum statusPagamento;
    private Boolean pago = false;
    private String photoName;
    private String imagemBase64;

    public GastoDTO(){}
    public GastoDTO(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime vencimento, String descricao, User user, Boolean deletado, LocalDate dataInicial, LocalDate dataFinal, BigDecimal valor, AgendaDePagamento agendaDePagamento, StatusPagamentoEnum statusPagamento, Boolean pago, String photoName, String imagemBase64) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vencimento = vencimento;
        this.descricao = descricao;
        this.user = user;
        this.deletado = deletado;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.valor = valor;
        AgendaDePagamento = agendaDePagamento;
        this.statusPagamento = statusPagamento;
        this.pago = pago;
        this.photoName = photoName;
        this.imagemBase64 = imagemBase64;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDateTime vencimento) {
        this.vencimento = vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getDeletado() {
        return deletado;
    }

    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public AgendaDePagamento getAgendaDePagamento() {
        return AgendaDePagamento;
    }

    public void setAgendaDePagamento(AgendaDePagamento agendaDePagamento) {
        AgendaDePagamento = agendaDePagamento;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamentoEnum statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }
}
