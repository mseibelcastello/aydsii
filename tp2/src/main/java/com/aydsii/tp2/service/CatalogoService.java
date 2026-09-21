package com.aydsii.tp2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aydsii.tp2.model.ProductoDTO;

@Service
public class CatalogoService {
    private final List<ProductoDTO> productos = new ArrayList<>(List.of(
            new ProductoDTO("P001", "Notebook", "Informatica", 850.50, 10),
            new ProductoDTO("P002", "Mouse", "Perifericos", 45.00, 30),
            new ProductoDTO("P003", "Teclado", "Perifericos", 120.00, 25),
            new ProductoDTO("P004", "Monitor 24", "Pantallas", 350.00, 8),
            new ProductoDTO("P005", "Auriculares", "Audio", 80.00, 15),
            new ProductoDTO("P006", "Pendrive 64GB", "Almacenamiento", 25.00, 50),
            new ProductoDTO("P007", "Webcam HD", "Perifericos", 60.00, 12),
            new ProductoDTO("P008", "Disco SSD 1TB", "Almacenamiento", 120.00, 6)));

    public List<ProductoDTO> listar() {
        return productos;
    }

    public List<ProductoDTO> buscar(
            String categoria,
            Double precioMin,
            Double precioMax) {

        return productos.stream()
                .filter(producto -> categoria == null || producto.getCategoria().equals(categoria))
                .filter(producto -> precioMin == null || producto.getPrecio() >= precioMin)
                .filter(producto -> precioMax == null || producto.getPrecio() <= precioMax)
                .toList();
    }

    public List<ProductoDTO> ordenar(String criterio, String ordenamiento) {

        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
        productos = listar();

        if (criterio.equals("nombre")) {
            productos.sort(Comparator.comparing(ProductoDTO::getNombre));
        } else {
            productos.sort(Comparator.comparing(ProductoDTO::getPrecio));
        }

        if (ordenamiento.equals("desc")) {
            Collections.reverse(productos);
        }

        return productos;
    }

    public ProductoDTO modificar(String id, int cantidad) {

        int nuevoStock = 0;
        ProductoDTO productoBuscado = null;

        for (ProductoDTO producto : productos) {
            if (producto.getId().equals(id)) {
                nuevoStock = producto.getStock() + cantidad;
                productoBuscado = producto;
                break;
            }
        }

        if (productoBuscado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede quedar en negativo");
        }

        productoBuscado.setStock(nuevoStock);

        return productoBuscado;
    }

}
