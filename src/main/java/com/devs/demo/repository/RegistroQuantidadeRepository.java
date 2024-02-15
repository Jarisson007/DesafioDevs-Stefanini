package com.devs.demo.repository;
import com.devs.demo.model.RegistroQuantidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroQuantidadeRepository extends JpaRepository<RegistroQuantidade, Long> {
    // Adicione métodos específicos, se necessário
}
