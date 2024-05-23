package com.repaso.producto.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioDto {

    private Long id;

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Número de teléfono no válido. Debe contener exactamente 10 dígitos.")
    private String celular;

    @NotBlank
    private String direccion;
}
