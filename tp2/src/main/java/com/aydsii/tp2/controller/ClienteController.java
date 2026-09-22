package com.aydsii.tp2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.aydsii.tp2.model.*;
import com.aydsii.tp2.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Cliente> crear(@RequestBody ClienteDTO cliente) {
        return new ApiResult<>(201, "Cliente creado con exito", clienteService.registrar(cliente));
    }

    @PostMapping("/validado")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Cliente> crearValidado(@Valid @RequestBody ClienteDTO cliente) {
        return new ApiResult<>(201, "Cliente creado con exito", clienteService.registrarValidado(cliente));
    }
}
