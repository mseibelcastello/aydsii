package com.aydsii.tp2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resultado de la conversión de una divisa")
public class ConversionResponse {

    @Schema(description = "Monto que se quiere convertir", example = "100")
    private Double montoOriginal;

    @Schema(description = "Código ISO de la moneda de origen", example = "USD")
    private String monedaOrigen;

    @Schema(description = "Código ISO de la moneda de destino", example = "EUR")
    private String monedaDestino;

    @Schema(description = "Valor de una unidad de la moneda de origen en la moneda de destino", example = "0.8874")
    private double tasaCambio;

    @Schema(description = "Monto ya convertido a la moneda de destino", example = "88.74")
    private double montoConvertido;

    @Schema(description = "Fecha de la cotización (yyyy-MM-dd)", example = "2026-10-06")
    private String fecha;

}
