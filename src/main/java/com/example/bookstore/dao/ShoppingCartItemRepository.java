package com.example.bookstore.dao;

import com.example.bookstore.bean.ShoppingCart;
import com.example.bookstore.bean.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    Optional<ShoppingCartItem> findByShoppingCartIdAndBookId(Long cartId, Long bookId);

    List<ShoppingCartItem> findAllByShoppingCartId(Long cartId);
}
