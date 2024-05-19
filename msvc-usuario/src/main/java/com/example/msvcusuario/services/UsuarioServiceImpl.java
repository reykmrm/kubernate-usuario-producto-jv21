package com.example.msvcusuario.services;

import com.example.msvcusuario.entity.Usuario;
import com.example.msvcusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        return usuarioRepository.findById(id);
    }

//    @Override
//    public Optional<Usuario> porEmail(String email) {
//        return Optional.empty();
//    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
