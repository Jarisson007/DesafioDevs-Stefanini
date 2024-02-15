package com.devs.demo.model;

public class RelatorioVendas {
    private int quantidade;
    private Long produtoId;
    private int quantidadeVendida;
    private double valorTotal;
    private String informacaoAdicional;
    private Long produto;


    // Construtor sem argumentos
    public RelatorioVendas() {
    }

    // Construtor
    public RelatorioVendas(Long produtoId, int quantidade, double valorTotal) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    // Construtor, getters e outros métodos...

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setInformacaoAdicional(String informacaoAdicional) {
        this.informacaoAdicional = informacaoAdicional;
    }
}

