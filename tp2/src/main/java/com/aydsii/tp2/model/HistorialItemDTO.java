package com.aydsii.tp2.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Cotización guardada en el historial de conversiones")
public class HistorialItemDTO {

    @Schema(description = "Fecha y hora de la consulta", example = "2026-10-06T15:30:00")
    private LocalDateTime fecha;

    @Schema(description = "Tasa de cambio obtenida en esa consulta", example = "0.8874")
    private BigDecimal tasaCambio;
}
