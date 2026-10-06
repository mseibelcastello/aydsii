package com.aydsii.tp2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aydsii.tp2.model.PedidoRespuestaDTO;
import com.aydsii.tp2.model.ProductoPedidoDTO;
import com.aydsii.tp2.model.tablas.Pedidos;
import com.aydsii.tp2.repository.PedidoRepository;
import java.math.BigDecimal;

@Service
public class PedidoService {

        private final PedidoRepository pedidoRepository;

        public PedidoService(PedidoRepository pedidoRepository) {
                this.pedidoRepository = pedidoRepository;
        }

        public List<PedidoRespuestaDTO> buscar(
                        Integer clienteId,
                        String categoria,
                        LocalDate fechaDesde,
                        LocalDate fechaHasta,
                        String estado) {

                return pedidoRepository.buscar(clienteId, categoria, fechaDesde, fechaHasta, estado)
                                .stream()
                                .map(this::convertir)
                                .toList();
        }

        private PedidoRespuestaDTO convertir(Pedidos pedido) {

                List<ProductoPedidoDTO> productos = pedido.getDetalles().stream()
                                .map(detalle -> {

                                        BigDecimal subtotal = detalle.getPrecioUnitario()
                                                        .multiply(BigDecimal.valueOf(detalle.getCantidad()));

                                        return new ProductoPedidoDTO(
                                                        detalle.getProducto().getNombre(),
                                                        detalle.getProducto().getCategoria().getNombre(),
                                                        detalle.getCantidad(),
                                                        subtotal);
                                })
                                .toList();

                BigDecimal totalPedido = productos.stream()
                                .map(ProductoPedidoDTO::getSubtotal)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                String nombreCliente = pedido.getCliente().getNombre()
                                + " "
                                + pedido.getCliente().getApellido();

                return new PedidoRespuestaDTO(
                                pedido.getId(),
                                nombreCliente,
                                pedido.getFechaPedido(),
                                pedido.getEstado(),
                                totalPedido,
                                productos);
        }
}