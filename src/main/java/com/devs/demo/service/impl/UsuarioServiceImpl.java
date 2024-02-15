package com.devs.demo.service.impl;
import com.devs.demo.model.Usuario;
import com.devs.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        // Verifica se o CPF já existe
        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // Se não existe, realiza o cadastro
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorCpf(String cpf) {

        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Adicione outros métodos conforme necessário
}