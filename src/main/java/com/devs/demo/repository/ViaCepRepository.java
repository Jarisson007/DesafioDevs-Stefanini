package com.devs.demo.repository;

import com.devs.demo.model.EnderecoViaCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaCepRepository extends JpaRepository<EnderecoViaCep, Long> {
    EnderecoViaCep findByCep(String cep);
    // adicione métodos específicos, se necessário
}
