package com.aydsii.tp2.model;

import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema (description = "Lista de ventas con descuento")
public class DescuentoDTO {
    private List<VentaDTO> ventas;
    private double totalConDescuento;

}
