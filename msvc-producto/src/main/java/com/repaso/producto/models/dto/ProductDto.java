package com.repaso.producto.models.dto;
import com.repaso.producto.models.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    @NotBlank
    private String codigo;
    @NotBlank
    private String nombre;
    @Size(min = 10, max = 300)
    private String descripcion;
    //cual es la anotacion correcta para validar un ENUM
    //cuando usamos enum??
    private Categoria categoria;
    @NotBlank
    private String marca;
    @Min(value = 0)
    private double peso;//permite campos vacios, not blank servira?
    private LocalDate fechaVencimiento; //funciona, las insersiones deben ser asi: yerar-month-day
}
