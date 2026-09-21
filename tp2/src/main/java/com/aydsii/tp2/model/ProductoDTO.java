package com.aydsii.tp2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema (description = "Productos en venta")
public class ProductoDTO {

    
    private String id;

    @NotBlank (message = "El producto debe tener un nombre")
    private String nombre;

    @NotBlank (message = "El producto debe tener una categoria")
    private String categoria;

    @Positive  (message = "El producto debe tener un precio")
    private double precio;

    @PositiveOrZero (message = "El stock debe ser mayor o igual que 0")
    private int stock;
}
