package com.aydsii.tp2.controller;

import com.aydsii.tp2.model.ApiResult;
import com.aydsii.tp2.model.PedidoRespuestaDTO;
import com.aydsii.tp2.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Historial de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Buscar pedidos", description = "Devuelve los pedidos que cumplen todos los filtros informados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta realizada (lista vacía si no hay coincidencias)"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping("/buscar")
    public ApiResult<List<PedidoRespuestaDTO>> buscar(
            @Parameter(description = "ID del cliente") @RequestParam(required = false) Integer clienteId,
            @Parameter(description = "Nombre de la categoría") @RequestParam(required = false) String categoria,
            @Parameter(description = "Fecha inicial (yyyy-MM-dd)") @RequestParam(required = false) LocalDate fechaDesde,
            @Parameter(description = "Fecha final (yyyy-MM-dd)") @RequestParam(required = false) LocalDate fechaHasta,
            @Parameter(description = "PENDIENTE, ENVIADO, ENTREGADO o CANCELADO") @RequestParam(required = false) String estado
    ) {
        return ApiResult.ok(pedidoService.buscar(clienteId, categoria, fechaDesde, fechaHasta, estado));
    }
}
