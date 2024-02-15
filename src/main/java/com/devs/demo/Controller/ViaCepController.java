package com.devs.demo.Controller;

import com.devs.demo.model.EnderecoViaCep;
import com.devs.demo.service.impl.ViaCepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viacep")
public class ViaCepController {

    @Autowired
    private ViaCepServiceImpl viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCep> buscarCep(@PathVariable String cep) {
        try {
            EnderecoViaCep endereco = viaCepService.buscarCep(cep);
            if (endereco != null) {
                return ResponseEntity.ok(endereco);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Trate qualquer exceção que possa ocorrer durante a busca do CEP
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

