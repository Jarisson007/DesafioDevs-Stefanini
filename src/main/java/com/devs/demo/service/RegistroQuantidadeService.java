package com.devs.demo.service;
import com.devs.demo.repository.RegistroQuantidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devs.demo.model.RegistroQuantidade;

@Service
public class RegistroQuantidadeService {

    @Autowired
    private RegistroQuantidadeRepository registroQuantidadeRepository;

    public RegistroQuantidade registrarQuantidade(RegistroQuantidade registroQuantidade) {
        // Implemente a lógica necessária e salve no banco de dados
        return registroQuantidadeRepository.save(registroQuantidade);
    }

}