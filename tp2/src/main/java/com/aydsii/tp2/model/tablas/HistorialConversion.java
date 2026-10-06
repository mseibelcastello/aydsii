package com.aydsii.tp2.model.tablas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historial_conversiones")
public class HistorialConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal monto;
    private BigDecimal montoConvertido;
    private BigDecimal tasa;
    private LocalDateTime fechaConsulta;

    @PrePersist
    public void prePersist() {
        this.fechaConsulta = LocalDateTime.now();
    }
}
