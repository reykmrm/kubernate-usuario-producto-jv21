package com.example.msvcusuario.services;

import com.example.msvcusuario.mapper.MaperUsuario;
import com.example.msvcusuario.models.entity.Usuario;
import com.example.msvcusuario.models.entity.dto.UsuarioDto;
import com.example.msvcusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MaperUsuario maperUsuario;


    @Override
    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDto> usuarioDtos = usuarios.stream().map(usuario
                -> maperUsuario.pasarDeUsuario(usuario)
        ).collect(Collectors.toList());
        return usuarioDtos;
    }

    @Override
    public UsuarioDto porId(Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);
        if (o.isPresent()) {
            return maperUsuario.pasarDeUsuario(o.get());
        }
        return null;
    }

    @Override
    public UsuarioDto porEmail(String email) {
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(email);
        if (existingUsuario.isPresent()) {
            return maperUsuario.pasarDeUsuario(existingUsuario.get());
        }
        return null;
    }

//    @Override
//    public Optional<Usuario> porEmail(String email) {
//        return Optional.empty();
//    }

    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Usuario usuario = usuarioRepository.save(maperUsuario.pasarDeUsurioDTO(usuarioDto));
        return maperUsuario.pasarDeUsuario(usuario);
    }

    @Override
    public UsuarioDto actualizar(UsuarioDto usuarioDto) {
        Optional<Usuario> existenteUsuario = usuarioRepository.findById(usuarioDto.getId());
        Optional<Usuario> existenteemail = usuarioRepository.findByEmail(usuarioDto.getEmail());
        if (!existenteUsuario.isPresent()) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        if (existenteemail.isPresent()) {
            if (existenteemail.get().getId() != usuarioDto.getId()) {
                throw new IllegalArgumentException("El email ya está registrado");
            }
        }

        Usuario usuario = usuarioRepository.save(maperUsuario.pasarDeUsurioDTO(usuarioDto));
        return maperUsuario.pasarDeUsuario(usuario);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Usuario> existenteUsuario = usuarioRepository.findById(id);
        if (!existenteUsuario.isPresent()) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }
}
