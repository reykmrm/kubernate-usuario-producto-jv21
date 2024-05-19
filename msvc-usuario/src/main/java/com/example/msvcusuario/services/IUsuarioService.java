package com.example.msvcusuario.services;

import com.example.msvcusuario.models.entity.Usuario;
import com.example.msvcusuario.models.entity.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<UsuarioDto> listar();

    UsuarioDto porId(Long id);

    UsuarioDto porEmail(String email);

    UsuarioDto guardar(UsuarioDto usuarioDto);
    UsuarioDto actualizar(UsuarioDto usuarioDto);

    void eliminar(Long id);

}
