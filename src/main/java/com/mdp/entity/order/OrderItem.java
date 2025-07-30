package com.mdp.entity.order;

import com.mdp.entity.product.Product;
import jakarta.persistence.*;

@Entity(name = "ORDER_ITEM")
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}
