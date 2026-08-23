package com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.mappers;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.enums.StatusPagamentoEnum;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.AgendaDePagamento;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GastoMapperTest {

    @Test
    void toGasto_shouldMapNestedIdsAndFields() {
        User user = new User();
        user.setId(7);
        user.setUsername("user");
        user.setPassword("secret");

        AgendaDePagamento agenda = new AgendaDePagamento();
        agenda.setId(99);

        GastoDTO dto = new GastoDTO(
                3,
                LocalDateTime.of(2026, 8, 23, 8, 0),
                LocalDateTime.of(2026, 8, 23, 9, 0),
                LocalDateTime.of(2026, 8, 30, 18, 0),
                "Cartao",
                user,
                false,
                null,
                null,
                new BigDecimal("123.45"),
                agenda,
                StatusPagamentoEnum.PAGO,
                true,
                "foto.png",
                "base64"
        );

        Gasto gasto = GastoMapper.toGasto(dto);

        assertEquals(dto.getId(), gasto.getId());
        assertEquals(dto.getDescricao(), gasto.getDescricao());
        assertEquals(dto.getUser().getId(), gasto.getUser().getId());
        assertEquals(dto.getAgendaDePagamento().getId(), gasto.getAgendaDePagamento().getId());
        assertEquals(dto.getValor(), gasto.getValor());
        assertEquals(dto.getStatusPagamento(), gasto.getStatusPagamento());
    }

    @Test
    void toGastoDTOList_shouldNullOutPasswords() {
        User user = new User();
        user.setId(7);
        user.setUsername("user");
        user.setPassword("secret");

        Gasto gasto = new Gasto();
        gasto.setId(1);
        gasto.setDescricao("Teste");
        gasto.setUser(user);

        List<GastoDTO> result = GastoMapper.toGastoDTOList(List.of(gasto));

        assertEquals(1, result.size());
        assertEquals("user", result.getFirst().getUser().getUsername());
        assertNull(result.getFirst().getUser().getPassword());
    }
}
