package com.example.bookstore.service;

import com.example.bookstore.bean.ShoppingCart;

import java.math.BigDecimal;

public interface IShoppingCartService {

    Boolean addShoppingCart(String userId, Long bookId, int quantity);

    ShoppingCart getShoppingCartItems(String userId);

    Double calculateTotalPrice(String userId);
}
