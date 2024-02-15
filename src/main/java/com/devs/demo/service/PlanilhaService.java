package com.devs.demo.service;

import com.devs.demo.model.RelatorioVendas;
import com.devs.demo.model.Vendas;
import com.devs.demo.repository.VendasRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PlanilhaService {

    @Autowired
    private VendasRepository vendasRepository;

    public void exportarVendasParaPlanilha(OutputStream outputStream) {
        try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {
            // Escrever cabeçalho
            csvWriter.writeNext(new String[]{"ID", "CPF do Usuário", "Quantidade", "Data da Compra"});

            // Obtém as vendas do banco de dados
            List<Vendas> vendas = vendasRepository.findAll();

            // Preenche o CSV com os dados de vendas
            for (Vendas venda : vendas) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(venda.getId()),
                        venda.getCpfUsuario(),
                        String.valueOf(venda.getQuantidade()),
                        venda.getDataCompra().toString()
                });
            }
        } catch (IOException e) {
            // Lida com exceções de IO, se necessário
            e.printStackTrace();
        }
    }


    public List<RelatorioVendas> gerarRelatorioVendasPorMes(int mes) {
        List<Vendas> vendasDoMes = vendasRepository.findByMes(mes);
        return calcularRelatorioVendas(vendasDoMes);
    }

    public List<RelatorioVendas> gerarRelatorioVendasPorUsuario(String cpf) {
        List<Vendas> vendasDoUsuario = vendasRepository.findByCpfUsuario(cpf);
        return calcularRelatorioVendas(vendasDoUsuario);
    }

    // Método auxiliar para calcular o relatório de vendas
    public List<RelatorioVendas> calcularRelatorioVendas(List<Vendas> vendas) {
        List<RelatorioVendas> relatorio = new ArrayList<>();
        for (Vendas venda : vendas) {
            // Adicione lógica para calcular as informações do relatório com base em cada venda
            double precoUnitario = obterPrecoUnitario(venda.getProdutoId());
            // Calcula o valor total com base no preço unitário e na quantidade vendida
            double valorTotal = precoUnitario * venda.getQuantidade();
            // Adiciona as informações no relatório
            relatorio.add(new RelatorioVendas(venda.getProdutoId(), venda.getQuantidade(), valorTotal));
        }
        return relatorio;
    }

    private double obterPrecoUnitario(Long produtoId) {
        // Lógica fictícia: considerando que tenha uma tabela de produtos com preços unitários
        // Aqui você precisaria consultar a tabela de produtos no seu banco de dados para obter o preço
        // Vou retornar um valor fixo de exemplo, que deverá substituir isso pela lógica futura real
        return 10.0; // Valor unitário fictício de exemplo
    }

    public void processarPlanilha(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            // Lê o cabeçalho (pode ser omitido se não houver cabeçalho no arquivo CSV)
            String[] header = csvReader.readNext();

            String[] row;
            while ((row = csvReader.readNext()) != null) {
                Vendas venda = extrairVendaDoCSVRow(row);
                vendasRepository.save(venda);
            }
        } catch (IOException e) {
            // Lide com exceções de leitura da planilha
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private Vendas extrairVendaDoCSVRow(String[] row) {
        // Lógica para converter os valores do CSV para uma instância de Vendas
        Vendas venda = new Vendas();
        venda.setId(Long.parseLong(row[0]));
        venda.setCpfUsuario(row[1]);
        venda.setQuantidade(Integer.parseInt(row[2]));

        // Adicionando lógica para extrair a data da quarta coluna (considerando que seja uma data válida)
        try {
            venda.setDataCompra(new SimpleDateFormat("dd-MM-yyyy").parse(row[3])); // Ajuste o formato conforme necessário
        } catch (ParseException e) {
            // Trate exceções de parsing de data, se necessário
            e.printStackTrace();
        }

        return venda;
    }
}






