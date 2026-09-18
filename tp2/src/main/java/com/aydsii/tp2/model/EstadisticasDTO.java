package com.aydsii.tp2.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Schema(description="Resultado del procesamiento de las ventas")
public class EstadisticasDTO {

    @Schema (description="Suma de todas las facturas")
    private double totalFacturado;

    @Schema (description="Cantidad de ventas realizadas")
    private int cantidadVentas;

    @Schema(description="Valor promedio de todos los tickets")
    private double ticketPromedio;

    @Schema(description="Venta de mayor importe")
    private VentaDTO ventaMayor;

    @Schema(description="Venta de menor importe")
    private VentaDTO ventaMenor;

    @Schema(description="Producto con mas unidades vendidas")
    private String productoMasVendido;

 

}
