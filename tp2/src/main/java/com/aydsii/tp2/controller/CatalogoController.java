package com.aydsii.tp2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.model.ApiResult;
import com.aydsii.tp2.model.ProductoDTO;
import com.aydsii.tp2.service.CatalogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/catalogo")
@Tag(name = "Catalogo", description = "Catalogo de productos")
public class CatalogoController {

    private CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }// inyeccion de dependencia

    // -------------------------------------------------------------------------
    @Operation(summary = "Listar productos", description = "Devuelve todos los productos del catalogo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    })
    @GetMapping
    public ApiResult<List<ProductoDTO>> listar() {
        return ApiResult.ok(catalogoService.listar());
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Buscar productos", description = "Devuelve los productos buscados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    })
    @GetMapping("/buscar")
    public ApiResult<List<ProductoDTO>> buscarProducto(
            @Parameter(description = "Categoria buscada") @RequestParam(required = false) String categoria,
            @Parameter(description = "Producto con precio minimo") @RequestParam(required = false) @Min(0) Double precioMin,
            @Parameter(description = "Producto con precio maximo") @RequestParam(required = false) Double precioMax

    ) {
        return ApiResult.ok(catalogoService.buscar(categoria, precioMin, precioMax));
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Ordenar productos", description = "Devuelve los productos ordenados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos ordenada")
    })
    @GetMapping("/ordenar")
    public ApiResult<List<ProductoDTO>> ordenarProductos(
            @Parameter(description = "Criterio de orden") @RequestParam(required = false) String criterio,
            @Parameter(description = "Tipo de ordenamiento") @RequestParam String ordenamiento

    ) {
        return ApiResult.ok(catalogoService.ordenar(criterio, ordenamiento));
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Modificar stock", description = "Modifica el stock segun la cantidad indicada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos ordenada"),
            @ApiResponse(responseCode = "400", description = "La modificacion provocó un stock negativo."),
            @ApiResponse(responseCode = "404", description = "ID no encontrado")
    })
    @PutMapping("/{id}")
    public ApiResult<ProductoDTO> modificarStock(
            @Parameter(description = "ID del producto") @PathVariable String id,
            @Parameter(description = "Cantidad a modificar") @RequestParam int cantidad

    ) {
        return ApiResult.ok(catalogoService.modificar(id, cantidad));
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    @Operation(summary = "Eliminar producto", description = "Elimina un producto de la coleccion segun el ID indicado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "ID no encontrado")
    })
    @DeleteMapping("/{id}")
    public ApiResult<Void> eliminar(
            @Parameter(description = "ID del producto") @PathVariable String id) {
            catalogoService.eliminar(id);
            return ApiResult.ok(null);
    }

}
