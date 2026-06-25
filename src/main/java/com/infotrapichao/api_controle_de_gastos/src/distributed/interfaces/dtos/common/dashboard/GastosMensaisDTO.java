package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record GastosMensaisDTO(Number mes, BigDecimal total) {
    @JsonProperty("mesAbreviado")
    public String mesAbreviado() {
        return switch (mes.intValue()) {
            case 1 -> "JAN";
            case 2 -> "FEV";
            case 3 -> "MAR";
            case 4 -> "ABR";
            case 5 -> "MAI";
            case 6 -> "JUN";
            case 7 -> "JUL";
            case 8 -> "AGO";
            case 9 -> "SET";
            case 10 -> "OUT";
            case 11 -> "NOV";
            case 12 -> "DEZ";
            default -> "";
        };
    }
}
