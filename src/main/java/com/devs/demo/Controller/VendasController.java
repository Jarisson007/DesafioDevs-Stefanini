package com.devs.demo.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devs.demo.model.RelatorioVendas;
import com.devs.demo.service.PlanilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {

    @Autowired
    private PlanilhaService planilhaService;

    // Mapeamento para processar planilha
    @PostMapping("/upload")
    public ResponseEntity<String> processarPlanilha(@RequestParam("file") MultipartFile file) {
        planilhaService.processarPlanilha(file);
        return ResponseEntity.ok("Planilha processada com sucesso!");
    }

    // Mapeamento para consultar relatório de vendas por mês e retornar como JSON
    @GetMapping("/relatorio-por-mes")
    public ResponseEntity<List<RelatorioVendas>> consultarRelatorioVendasPorMes(@RequestParam int mes) {
        List<RelatorioVendas> relatorio = planilhaService.gerarRelatorioVendasPorMes(mes);
        return ResponseEntity.ok(relatorio);
    }

    // Mapeamento para consultar relatório de vendas por usuário
    @GetMapping("/relatorio-por-usuario")
    public List<RelatorioVendas> consultarRelatorioVendasPorUsuario(@RequestParam String cpf) {
        return planilhaService.gerarRelatorioVendasPorUsuario(cpf);
    }

    // Endpoint para baixar a planilha
    @GetMapping("/exportar-planilha")
    public ResponseEntity<byte[]> exportarPlanilha() {
        // Criação do ByteArrayOutputStream para armazenar a planilha em memória
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Chama o serviço para exportar as vendas para a planilha
        planilhaService.exportarVendasParaPlanilha(outputStream);

        // Cria um array de bytes a partir do ByteArrayOutputStream
        byte[] bytes = outputStream.toByteArray();

        // Configura os cabeçalhos da resposta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "vendas.xlsx");

        // Retorna a resposta HTTP com o array de bytes e os cabeçalhos configurados
        return ResponseEntity.ok().headers(headers).body(bytes);
    }
}
