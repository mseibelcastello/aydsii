package com.aydsii.tp2.model;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate fechaPedido;

    private String estado;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles;
}