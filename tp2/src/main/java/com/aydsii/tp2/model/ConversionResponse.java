package com.aydsii.tp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResponse {
    private Double montoOriginal;
    private String monedaOrigen;
    private String monedaDestino;
    private double tasaCambio;
    private double montoConvertido;
    private String fecha;

}
