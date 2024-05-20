package com.repaso.producto.models.entity;
import com.repaso.producto.models.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo; // como darle x caracteristica, zerofil
    @NotBlank
    private String nombre;
    //tamaño
    @NotBlank
    @Size(min = 10, max = 300)//creo que VARCHAR SOLO VA HASTA 255
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "categorias")
    private Categoria categoria; //al ser enum sera menu desplegable
    @NotBlank //verificar si esta etiqueta cubre un valor NULL
    private String marca;
    @Min(value = 0)
    private float peso;

    @Temporal(TemporalType.DATE)
    private LocalDate fechaVencimiento;
}
