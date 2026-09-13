package com.aydsii.tp2.controller;

import com.aydsii.tp2.model.*;
import com.aydsii.tp2.service.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas/estadisticas")

public class VentaController {

    private VentaService ventaService = new VentaService();

    @PostMapping
    public EstadisticasDTO obtenerEstadisticas(@RequestBody @Valid List<VentaDTO> ventas) {
        return ventaService.obtenerEstadisticas(ventas);
    }



}
