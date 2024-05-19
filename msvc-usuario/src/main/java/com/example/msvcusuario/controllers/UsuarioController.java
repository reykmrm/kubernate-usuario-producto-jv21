package com.example.msvcusuario.controllers;


import com.example.msvcusuario.models.entity.Usuario;
import com.example.msvcusuario.models.entity.dto.UsuarioDto;
import com.example.msvcusuario.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public UsuarioDto porId(@PathVariable Long id) {
        return usuarioService.porId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioDto));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result, @PathVariable Long id) {
        if (id != usuarioDto.getId()) {
            return new ResponseEntity<>("El ID que intenta actualizar es inválido", HttpStatus.BAD_REQUEST);
        }
        if (result.hasErrors()) {
            return validar(result);
        }
        try {
            UsuarioDto actualizado = usuarioService.actualizar(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(actualizado);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error inesperado" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error inesperado " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campon " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
