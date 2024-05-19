package com.example.msvcusuario.controllers;


import com.example.msvcusuario.entity.Usuario;
import com.example.msvcusuario.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

}
