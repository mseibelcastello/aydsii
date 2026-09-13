package com.aydsii.tp2.dto;

public class EstadisticasDTO {

    private double totalFacturado;
    private int cantidadVentas;
    private double ticketPromedio;
    private VentaDTO ventaMayor;
    private VentaDTO ventaMenor;
    private String productoMasVendido;

    public double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getTicketPromedio() {
        return ticketPromedio;
    }

    public void setTicketPromedio(double ticketPromedio) {
        this.ticketPromedio = ticketPromedio;
    }

    public VentaDTO getVentaMayor() {
        return ventaMayor;
    }

    public void setVentaMayor(VentaDTO ventaMayor) {
        this.ventaMayor = ventaMayor;
    }

    public VentaDTO getVentaMenor() {
        return ventaMenor;
    }

    public void setVentaMenor(VentaDTO ventaMenor) {
        this.ventaMenor = ventaMenor;
    }

    public String getProductoMasVendido() {
        return productoMasVendido;
    }

    public void setProductoMasVendido(String productoMasVendido) {
        this.productoMasVendido = productoMasVendido;
    }

}
