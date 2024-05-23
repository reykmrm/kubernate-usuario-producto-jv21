package com.repaso.producto.models.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.repaso.producto.models.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String codigo;
    private String nombre;
    private String descripcion;
    @Column(name = "categorias")
    private Categoria categoria;
    private String marca;
    private double peso;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaVencimiento;

    //FK
    //seria una relacion de uno a muchos, List<Ususarios> listUsuario;
    private Long idUsuario;
}
