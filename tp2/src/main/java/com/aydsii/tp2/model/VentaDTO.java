package com.aydsii.tp2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor 
@Schema (description= "Venta recibida para procesar") 

public class VentaDTO {

    @NotBlank(message = "El producto no puede estar vacio")
    private String producto;

    @Positive (message = "La cantidad debe ser mayor a 0")
    private int cantidad;

    @Positive(message = "El precio unitario debe ser mayor a 0")
    private double precioUnitario;

    private double montoConDescuento;


}



