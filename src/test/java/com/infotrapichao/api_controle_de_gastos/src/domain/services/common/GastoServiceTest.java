package com.infotrapichao.api_controle_de_gastos.src.domain.services.common;

import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.GastoDTO;
import com.infotrapichao.api_controle_de_gastos.src.distributed.interfaces.dtos.common.get.dashboard.GastosMensaisDTO;
import com.infotrapichao.api_controle_de_gastos.src.domain.models.common.Gasto;
import com.infotrapichao.api_controle_de_gastos.src.infrastruture.repositories.common.GastoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        when(gastoRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new Gasto())));

        Page<Gasto> gastos = gastoService.findAllByFilter(filter, 0, 20);

        assertEquals(1, gastos.getContent().size());
        verify(gastoRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = 2024)
    void findTotaisPorMes_shouldDelegateUsingUserIdAndSelectedYear(Integer ano) {
        GastoDTO filter = new GastoDTO();
        filter.setUser(new com.infotrapichao.api_controle_de_gastos.src.domain.models.security.User());
        filter.getUser().setId(9);

        int anoEsperado = ano == null ? Year.now().getValue() : ano;
        when(gastoRepository.findTotaisPorMes(9, anoEsperado))
                .thenReturn(List.of(new GastosMensaisDTO(8, new BigDecimal("200.00"))));

        List<GastosMensaisDTO> totais = gastoService.findTotaisPorMes(filter, ano);

        assertEquals(1, totais.size());
        assertEquals(new BigDecimal("200.00"), totais.getFirst().total());
        verify(gastoRepository).findTotaisPorMes(9, anoEsperado);
    }
}
