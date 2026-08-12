package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.created;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailCreatedRequestDTO {

    private String descricao;
    private String nomeUsuario;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String remetente;
    private String destinatario;
    private String assunto;
    private String corpo;
    private GastoRequestDTO filters;
    private ByteArrayOutputStream file;

}