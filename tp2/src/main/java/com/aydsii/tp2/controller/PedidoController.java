package com.aydsii.tp2.controller;

import com.aydsii.tp2.model.ApiResult;
import com.aydsii.tp2.model.PedidoRespuestaDTO;
import com.aydsii.tp2.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscar")
    public ApiResult<List<PedidoRespuestaDTO>> buscar(
            @RequestParam(required = false) Integer clienteId,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) LocalDate fechaDesde,
            @RequestParam(required = false) LocalDate fechaHasta,
            @RequestParam(required = false) String estado
    ) {

        List<PedidoRespuestaDTO> pedidos = pedidoService.buscar(
                clienteId,
                categoria,
                fechaDesde,
                fechaHasta,
                estado
        );

        return new ApiResult<>(
                200,
                "Consulta realizada correctamente",
                pedidos
        );
    }
}