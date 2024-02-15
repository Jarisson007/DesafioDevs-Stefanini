package com.devs.demo.model;

public class QuantidadeDTO {

    private int quantidade;
    private int mes;

    // Construtores, getters e setters

    public QuantidadeDTO() {
        // Construtor padrão necessário para deserialização JSON
    }

    public QuantidadeDTO(int quantidade, int mes) {
        this.quantidade = quantidade;
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}

