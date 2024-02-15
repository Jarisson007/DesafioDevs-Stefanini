package com.devs.demo.Controller;

import com.devs.demo.service.impl.RelatorioServiceImpl;
import com.devs.demo.model.RelatorioVendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioServiceImpl relatorioService;

    @GetMapping("/vendas/{mes}")
    public ResponseEntity<List<RelatorioVendas>> gerarRelatorioVendasPorMes(@PathVariable int mes) {
        List<RelatorioVendas> relatorioVendas = relatorioService.gerarRelatorioVendasPorMes(mes);
        // Verifique se o relatório não está vazio ou nulo e retorne a resposta adequada
        if (relatorioVendas != null && !relatorioVendas.isEmpty()) {
            return ResponseEntity.ok(relatorioVendas);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Adicione outros métodos conforme necessário para outros tipos de relatórios
}
