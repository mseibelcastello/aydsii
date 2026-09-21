package com.aydsii.tp2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.model.ApiResult;
import com.aydsii.tp2.model.EstadisticasDTO;
import com.aydsii.tp2.model.*;
import com.aydsii.tp2.service.VentaService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/ventas")

@Tag(name = "Ventas", description = "Estadisticas y descuentos de ventas")

public class VentaController {

        private VentaService ventaService = new VentaService();

        @Operation(summary = "Obtener estadisticas", description = "Devuelve las estadisticas de una lista de ventas")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Estadisticas calucladas"),
                        @ApiResponse(responseCode = "400", description = "Datos invalidos")
        })
        @PostMapping("/estadisticas")
        public ApiResult<EstadisticasDTO> obtenerEstadisticas(
                        @Parameter(description = "Lista de ventas") @Valid @RequestBody @NotEmpty(message = "La lista de ventas no puede estar vacia") List<@Valid VentaDTO> ventas) {
                return ApiResult.ok(ventaService.obtenerEstadisticas(ventas));
        }

        @Operation(summary = "Aplicar descuento", description = "Devuelve el descuento de una lista de ventas")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Descuento aplicado"),
                        @ApiResponse(responseCode = "400", description = "Porcentaje invalido")
        })

        @PostMapping("/aplicar-descuento")
        public ApiResult<DescuentoDTO> aplicarDescuento(
                        @Parameter(description = "Lista de ventas") @Valid @RequestBody @NotEmpty(message = "La lista de ventas no puede estar vacia") List<@Valid VentaDTO> ventas,
                        @Parameter(description = "Descuento a aplicar") @RequestParam @Min(0) @Max(100) int descuento) {

                return ApiResult.ok(ventaService.obtenerDescuento(ventas, descuento));
        }

}