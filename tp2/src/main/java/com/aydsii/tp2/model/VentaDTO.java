package com.aydsii.tp2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class VentaDTO {

    @NotBlank(message = "no puede estar vacio")
    private String producto;

    @Positive (message = "debe ser mayor a 0")
    private int cantidad;

    @Positive(message = "debe ser mayor a 0")
    private double precioUnitario;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}



