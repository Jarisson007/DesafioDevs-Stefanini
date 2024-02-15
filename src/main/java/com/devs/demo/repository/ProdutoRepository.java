package com.devs.demo.repository;

import com.devs.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodigo(String codigo);
    // adicione métodos específicos, se necessário
}
