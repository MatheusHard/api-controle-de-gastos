package com.infotrapichao.api_controle_de_gastos.src.domain.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common.GastoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GastoServiceTest {

    @Mock
    private GastoRepository gastoRepository;

    @InjectMocks
    private GastoService gastoService;

    @Test
    void create_shouldSaveExpense() {
        Gasto gasto = new Gasto();
        gasto.setDescricao("Internet");

        when(gastoRepository.save(any(Gasto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Gasto saved = gastoService.create(gasto);

        assertEquals("Internet", saved.getDescricao());
        verify(gastoRepository).save(gasto);
    }

    @Test
    void findAllByFilter_shouldDelegateToSpecificationSearch() {
        GastoDTO filter = new GastoDTO();
        when(gastoRepository.findAll(any(Specification.class))).thenReturn(List.of(new Gasto()));

        List<Gasto> gastos = gastoService.findAllByFilter(filter);

        assertEquals(1, gastos.size());
        verify(gastoRepository).findAll(any(Specification.class));
    }

    @Test
    void findTotaisPorMes_shouldDelegateUsingUserId() {
        GastoDTO filter = new GastoDTO();
        filter.setUser(new com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User());
        filter.getUser().setId(9);

        when(gastoRepository.findTotaisPorMes(9)).thenReturn(List.of(new GastosMensaisDTO(8, new BigDecimal("200.00"))));

        List<GastosMensaisDTO> totais = gastoService.findTotaisPorMes(filter);

        assertEquals(1, totais.size());
        assertEquals(new BigDecimal("200.00"), totais.getFirst().total());
        verify(gastoRepository).findTotaisPorMes(9);
    }
}
