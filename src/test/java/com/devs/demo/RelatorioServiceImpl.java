package com.devs.demo;

import com.devs.demo.model.RelatorioVendas;
import com.devs.demo.model.Vendas;
import com.devs.demo.repository.VendasRepository;
import com.devs.demo.service.impl.RelatorioServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RelatorioServiceImplTest {

    @Mock
    private VendasRepository vendasRepository;

    @InjectMocks
    private RelatorioServiceImpl relatorioService;

    @Test
    void testGerarRelatorioVendasPorMes() {
        // Simule o retorno do repositório
        when(vendasRepository.findByMes(1)).thenReturn(Collections.singletonList(new Vendas()));

        // Inicialize os mocks
        MockitoAnnotations.initMocks(this);

        // Chame o método que você deseja testar
        List<RelatorioVendas> relatorioVendas = relatorioService.gerarRelatorioVendasPorMes(1);

        // Adicione as verificações necessárias
        assertEquals(1, relatorioVendas.size());
    }
}

