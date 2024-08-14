package com.example.bookstore.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@Data
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> items;

}