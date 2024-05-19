package com.example.msvcusuario.mapper;

import com.example.msvcusuario.models.entity.Usuario;
import com.example.msvcusuario.models.entity.dto.UsuarioDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MaperUsuario {
    public Usuario pasarDeUsurioDTO(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto,usuario);
        return usuario;
    }

    public UsuarioDto pasarDeUsuario(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        BeanUtils.copyProperties(usuario,usuarioDto);
        return usuarioDto;
    }
}
