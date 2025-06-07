package com.mdp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "PEDIDOS")
@Table(name = "PEDIDOS")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
