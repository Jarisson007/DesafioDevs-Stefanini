package com.devs.demo.repository;

import com.devs.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    // adicione outros métodos específicos, se necessário
}
