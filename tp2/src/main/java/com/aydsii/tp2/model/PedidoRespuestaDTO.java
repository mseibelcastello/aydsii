package com.aydsii.tp2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Pedido con los datos de su cliente y sus productos")
public class PedidoRespuestaDTO {

    @Schema(description = "ID del pedido", example = "12")
    private Integer pedidoId;

    @Schema(description = "Nombre y apellido del cliente", example = "Ana Garcia")
    private String cliente;

    @Schema(description = "Fecha del pedido (yyyy-MM-dd)", example = "2026-08-15")
    private LocalDate fecha;

    @Schema(description = "Estado del pedido: PENDIENTE, ENVIADO, ENTREGADO o CANCELADO", example = "ENTREGADO")
    private String estado;

    @Schema(description = "Suma de los subtotales de los productos", example = "25400.0")
    private BigDecimal totalPedido;

    @Schema(description = "Productos incluidos en el pedido")
    private List<ProductoPedidoDTO> productos;
}
