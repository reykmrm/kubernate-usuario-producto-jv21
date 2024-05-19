package com.example.msvcusuario.models.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Número de teléfono no válido. Debe contener exactamente 10 dígitos.")
    private String celular;

    @NotBlank
    private String direccion;

}
