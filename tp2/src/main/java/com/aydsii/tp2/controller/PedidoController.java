package com.aydsii.tp2.controller;

import com.aydsii.tp2.service.PedidoService;

import com.aydsii.tp2.model.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscar")
    public ApiResult<List<PedidoRespuestaDTO>> buscar(
            @RequestParam(required = false) Integer clienteId) {

        return null;
    }
}
