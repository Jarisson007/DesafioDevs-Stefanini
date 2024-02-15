package com.devs.demo.service.impl;

import com.devs.demo.model.Vendas;
import com.devs.demo.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasServiceImpl {

    @Autowired
    private VendasRepository vendasRepository;

    public Vendas registrarVenda(Vendas venda) {
        return vendasRepository.save(venda);
    }

    public Vendas buscarVendaPorCpfUsuario(String cpfUsuario) {
        List<Vendas> vendas = vendasRepository.findByCpfUsuario(cpfUsuario);
        return vendas.isEmpty() ? null : vendas.get(0);
    }

    // Implemente outros métodos conforme necessário
}
