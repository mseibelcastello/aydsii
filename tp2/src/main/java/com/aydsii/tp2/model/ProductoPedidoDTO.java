package com.aydsii.tp2.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Producto incluido en un pedido")
public class ProductoPedidoDTO {

    @Schema(description = "Nombre del producto", example = "Mouse inalambrico")
    private String nombre;

    @Schema(description = "Categoría del producto", example = "Perifericos")
    private String categoria;

    @Schema(description = "Unidades pedidas", example = "2")
    private Integer cantidad;

    @Schema(description = "Precio unitario multiplicado por la cantidad", example = "9000.0")
    private BigDecimal subtotal;
}
