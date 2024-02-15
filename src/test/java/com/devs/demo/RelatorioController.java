package com.devs.demo;

import com.devs.demo.Controller.RelatorioController;
import com.devs.demo.model.RelatorioVendas;
import com.devs.demo.service.impl.RelatorioServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RelatorioControllerTest {

    @Mock
    private RelatorioServiceImpl relatorioService;

    @InjectMocks
    private RelatorioController relatorioController;

    @Test
    void testGerarRelatorioVendasPorMes() {
        // Simule o retorno do serviço
        when(relatorioService.gerarRelatorioVendasPorMes(1))
                .thenReturn(Collections.singletonList(new RelatorioVendas()));

        // Inicialize os mocks
        MockitoAnnotations.initMocks(this);

        // Chame o método que você deseja testar
        ResponseEntity<List<RelatorioVendas>> responseEntity = relatorioController.gerarRelatorioVendasPorMes(1);

        // Adicione as verificações necessárias
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }
}

