package com.devs.demo;

import com.devs.demo.model.Vendas;
import com.devs.demo.repository.VendasRepository;
import com.devs.demo.service.impl.VendasServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class VendasServiceImplTest {

    @Mock
    private VendasRepository vendasRepository;

    @InjectMocks
    private VendasServiceImpl vendasService;

    @Test
    void testRegistrarVenda() {
        // Inicialize os mocks
        MockitoAnnotations.openMocks(this);

        // Crie um exemplo de venda
        Vendas venda = new Vendas();

        // Chame o método que você deseja testar
        vendasService.registrarVenda(venda);

        // Verifique se o método do repositório foi chamado corretamente
        verify(vendasRepository).save(venda);
    }
}

