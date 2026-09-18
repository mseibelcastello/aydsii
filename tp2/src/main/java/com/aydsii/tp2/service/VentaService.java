package com.aydsii.tp2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aydsii.tp2.model.EstadisticasDTO;
import com.aydsii.tp2.model.VentaDTO;

@Service
public class VentaService {

    public EstadisticasDTO obtenerEstadisticas(List<VentaDTO> ventas) {
        double importe = 0;
        double totalFacturado = 0;
        int cantidadVentas = 0;
        double importeMayor = 0;
        double importeMenor = 0;
        VentaDTO ventaMayor = null, ventaMenor = null;

        Map<String, Integer> cantidadesPorProducto = new HashMap<>();
        String productoMasVendido = null;

        if (ventas == null || ventas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ventas no puede estar vacia");
        }

        for (VentaDTO venta : ventas) {
            importe = venta.getCantidad() * venta.getPrecioUnitario();
            totalFacturado = totalFacturado + importe;
            cantidadVentas++;

            if (cantidadVentas == 1) {
                importeMayor = importe;
                importeMenor = importe;
                ventaMayor = venta;
                ventaMenor = venta;
            }

            if (importe > importeMayor) {
                importeMayor = importe;
                ventaMayor = venta;
            }

            if (importe < importeMenor) {
                importeMenor = importe;
                ventaMenor = venta;
            }

            String producto = venta.getProducto();
            int cantidad = venta.getCantidad();

            if (cantidadesPorProducto.containsKey(producto)) {
                int nuevo = cantidadesPorProducto.get(producto) + cantidad;
                cantidadesPorProducto.put(producto, nuevo);

            } else {

                cantidadesPorProducto.put(producto, cantidad);

            }

        }

        double ticketPromedio = totalFacturado / cantidadVentas;

        int i = 0;
        int cantidadMasVendida = 0;
        for (Map.Entry<String, Integer> entrada : cantidadesPorProducto.entrySet()) {

            if (i == 0) {
                productoMasVendido = entrada.getKey();
                cantidadMasVendida = entrada.getValue();

            } else {
                if (entrada.getValue() > cantidadMasVendida) {
                    productoMasVendido = entrada.getKey();
                    cantidadMasVendida = entrada.getValue();
                }
            }
            i++;
        }

        EstadisticasDTO estadisticas = new EstadisticasDTO();

        estadisticas.setTotalFacturado(totalFacturado);
        estadisticas.setCantidadVentas(cantidadVentas);
        estadisticas.setTicketPromedio(ticketPromedio);
        estadisticas.setVentaMayor(ventaMayor);
        estadisticas.setVentaMenor(ventaMenor);
        estadisticas.setProductoMasVendido(productoMasVendido);

        return estadisticas;

    }
}
