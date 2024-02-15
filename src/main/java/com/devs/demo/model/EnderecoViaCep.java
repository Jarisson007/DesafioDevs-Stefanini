package com.devs.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EnderecoViaCep {
    @Id
    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    // Construtores, getters e setters
}
