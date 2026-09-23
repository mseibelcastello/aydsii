package com.aydsii.tp2.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoPedidoDTO {

    private String nombre;
    private String categoria;
    private Integer cantidad;
    private BigDecimal subtotal;
}