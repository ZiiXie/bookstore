package com.example.bookstore.service.impl;

import com.example.bookstore.bean.Book;
import com.example.bookstore.bean.ShoppingCart;
import com.example.bookstore.bean.ShoppingCartItem;
import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.dao.ShoppingCartItemRepository;
import com.example.bookstore.dao.ShoppingCartRepository;
import com.example.bookstore.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartItemRepository shoppingCartRepositoryItem;

    @Override
    public Boolean addShoppingCart(String userId, Long bookId, int quantity) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            return false;
        }
        Book book = bookOptional.get();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
            .orElseGet(() -> {
                ShoppingCart newCart = new ShoppingCart();
                newCart.setUserId(userId);
                return shoppingCartRepository.save(newCart);
            });

        Optional<ShoppingCartItem> existingCartItemOptional = shoppingCartRepositoryItem.findByShoppingCartIdAndBookId(shoppingCart.getId(), bookId);
        if (existingCartItemOptional.isPresent()) {
            ShoppingCartItem existingCartItem = existingCartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            shoppingCartRepositoryItem.save(existingCartItem);
        } else {
            ShoppingCartItem cartItem = new ShoppingCartItem();
            cartItem.setShoppingCart(shoppingCart);
            cartItem.setBook(book);
            cartItem.setQuantity(quantity);
            shoppingCartRepositoryItem.save(cartItem);
        }
        return true;
    }

    @Override
    public ShoppingCart getShoppingCartItems(String userId) {
        ShoppingCart shoppingCart =  shoppingCartRepository.findByUserId(userId).orElse(null);
        return shoppingCart;
    }

    @Override
    public Double calculateTotalPrice(String userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElse(null);
        if (shoppingCart == null) {
            return 0d;
        }
        List<ShoppingCartItem> cartItems = shoppingCartRepositoryItem.findAllByShoppingCartId(shoppingCart.getId());

        double totalPrice = 0.0;
        for (ShoppingCartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            double itemTotalPrice = book.getPrice() * cartItem.getQuantity();
            totalPrice += itemTotalPrice;
        }
        return totalPrice;
    }
}
