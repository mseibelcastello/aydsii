package com.aydsii.tp2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.aydsii.tp2.model.*;
import com.aydsii.tp2.model.tablas.Cliente;
import com.aydsii.tp2.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Alta de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Alta simple", description = "Registra un cliente sin validaciones")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Cliente> crear(@RequestBody ClienteDTO cliente) {
        return new ApiResult<>(201, "Cliente creado con éxito", clienteService.registrar(cliente));
    }

    @Operation(summary = "Alta con validación", description = "Registra un cliente validando los datos y que el email no esté registrado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o email ya registrado")
    })
    @PostMapping("/validado")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Cliente> crearValidado(@Valid @RequestBody ClienteDTO cliente) {
        return new ApiResult<>(201, "Cliente creado con éxito", clienteService.registrarValidado(cliente));
    }
}
