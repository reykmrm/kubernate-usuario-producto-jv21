package com.example.msvcusuario.models.entity.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
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
