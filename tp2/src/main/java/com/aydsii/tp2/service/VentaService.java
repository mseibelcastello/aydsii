package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.EstadisticasDTO;
import com.aydsii.tp2.dto.VentaDTO;

import java.util.*;

public class VentaService {

    public EstadisticasDTO obtenerEstadisticas(List<VentaDTO> ventas) {
        double importe = 0;
        double totalFacturado = 0;
        int cantidadVentas = 0;
        double importeMayor = 0;
        double importeMenor = 0;
        VentaDTO ventaMayor=null, ventaMenor=null;

        Map<String, Integer> cantidadesPorProduto = new HashMap<>();
        String productoMasVendido=null;

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

            if (cantidadesPorProduto.containsKey(producto)) {
                int nuevo = cantidadesPorProduto.get(producto) + cantidad;
                cantidadesPorProduto.put(producto, nuevo);

            } else {

                cantidadesPorProduto.put(producto, cantidad);

            }

        }

        double ticketPromedio = totalFacturado / cantidadVentas;

        int i= 0;
        int cantidadMasVedida=0;
        for (Map.Entry<String, Integer> entrada : cantidadesPorProduto.entrySet()){

            if(i==0){
                productoMasVendido=entrada.getKey();
                cantidadMasVedida=entrada.getValue();

            }else{
                if(entrada.getValue()>cantidadMasVedida){
                    productoMasVendido=entrada.getKey();
                    cantidadMasVedida=entrada.getValue();
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
