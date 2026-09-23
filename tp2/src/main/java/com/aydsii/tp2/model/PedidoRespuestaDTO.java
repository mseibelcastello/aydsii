package com.aydsii.tp2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoRespuestaDTO {

    private Integer pedidoId;
    private String cliente;
    private LocalDate fecha;
    private String estado;
    private BigDecimal totalPedido;
    private List<ProductoPedidoDTO> productos;
}