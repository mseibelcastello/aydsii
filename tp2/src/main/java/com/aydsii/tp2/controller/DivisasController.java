package com.aydsii.tp2.controller;

import com.aydsii.tp2.model.ApiResult;
import com.aydsii.tp2.model.ConversionResponse;
import com.aydsii.tp2.service.ConversionService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/divisas")
@Tag(name = "Divisas", description = "Conversiones de divisas")

public class DivisasController {
    private final ConversionService conversionService;

    public DivisasController(ConversionService conversorService) {
        this.conversionService = conversorService;
    }

    @Operation(summary = "Convertir", description = "Devuelve el valor convertido")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Valor convertido exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos invalidos")
        })

    @GetMapping("/convertir")
    public ApiResult<ConversionResponse> convertir(
        @Parameter (description = "Monto a convertir") @RequestParam @Positive (message="El monto debe ser mayor que 0") Double monto,
        @Parameter (description = "Moneda origen") @RequestParam @Pattern (regexp = "[A-Za-z]{3}", message = "La moneda debe tener exactamente 3 letras"
) String origen,
        @Parameter (description = "Moneda destino") @RequestParam @Pattern (regexp = "[A-Za-z]{3}", message = "La moneda debe tener exactamente 3 letras"
) String destino
    ){
        return ApiResult.ok(conversionService.convertir(monto, origen, destino));
    }


}

