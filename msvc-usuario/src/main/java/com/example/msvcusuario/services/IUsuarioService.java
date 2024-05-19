package com.example.msvcusuario.services;

import com.example.msvcusuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
//    Optional<Usuario> porEmail(String email);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
