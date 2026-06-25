package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common;

import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class AgendaDePagamentoDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User user;
    private Boolean deletado;
    private List<Gasto> gastos;

    ///Filters
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public AgendaDePagamentoDTO() {
    }

    public AgendaDePagamentoDTO(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User user, Boolean deletado, List<Gasto> gastos, LocalDate dataInicial, LocalDate dataFinal) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.deletado = deletado;
        this.gastos = gastos;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
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

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
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
}
