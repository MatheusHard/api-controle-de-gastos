package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums;


public enum StatusPagamentoEnum {
    NAO_PAGO(0),
    VENCIDO(1),
    PAGO(2);

    private final int codigo;

    public int getCodigo() {
        return codigo;
    }

    StatusPagamentoEnum(int codigo) {
        this.codigo = codigo;
    }

    public static StatusPagamentoEnum fromCodigo(int codigo) {
        for (StatusPagamentoEnum status : StatusPagamentoEnum.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}
