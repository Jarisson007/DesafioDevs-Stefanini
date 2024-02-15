package com.devs.demo.service.impl;

import com.devs.demo.model.EnderecoViaCep;
import com.devs.demo.repository.ViaCepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepServiceImpl {

    @Autowired
    private ViaCepRepository viaCepRepository;

    @Value("${viacep.url}")
    private String viaCepUrl;  // Certifique-se de ter essa propriedade no seu application.properties

    public EnderecoViaCep consultarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s/json", viaCepUrl, cep);

        return restTemplate.getForObject(url, EnderecoViaCep.class);
    }

    public EnderecoViaCep buscarCep(String cep) {
        // Tenta buscar no banco de dados
        EnderecoViaCep endereco = viaCepRepository.findByCep(cep);

        // Se não encontrar, busca na API ViaCep
        if (endereco == null) {
            endereco = consultarEnderecoPorCep(cep);

            // Se encontrou na API, salva no banco
            if (endereco != null) {
                viaCepRepository.save(endereco);
            }
        }

        return endereco;
    }
}
