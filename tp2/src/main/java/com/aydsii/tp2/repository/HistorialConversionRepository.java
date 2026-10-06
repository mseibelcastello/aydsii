package com.aydsii.tp2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aydsii.tp2.model.tablas.HistorialConversion;

public interface HistorialConversionRepository extends JpaRepository<HistorialConversion, Integer> {
    List<HistorialConversion> findByMonedaOrigenAndMonedaDestinoOrderByFechaConsultaDesc(
            String monedaOrigen, String monedaDestino);
}
