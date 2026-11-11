package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.security.get;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.AgendaDePagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String email;
    private final String password = null;
    private final List<AgendaDePagamentoDTO> agendaDePagamentos = null;
    private List<String> roles;
    private List<GrantedAuthority> authorities;
}
