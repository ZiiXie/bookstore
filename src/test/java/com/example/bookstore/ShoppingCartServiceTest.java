package com.example.bookstore;

import com.example.bookstore.bean.Book;
import com.example.bookstore.bean.ShoppingCart;
import com.example.bookstore.service.IBookService;
import com.example.bookstore.service.IShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ShoppingCartServiceTest {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Test
    void testAddShoppingCart() {
        // 先新增一个Book
        Book newBook = new Book();
        newBook.setTitle("title 1");
        newBook.setAuthor("author 1");
        newBook.setPrice(1d);
        newBook.setCategory("cate 1");
        // 调用服务方法
        Long bookId = bookService.addBook(newBook);
        // 假设我们有一个用户ID
        String userId = "xxx";
        // 调用服务方法
        boolean added = shoppingCartService.addShoppingCart(userId, bookId, 2);
        // 验证结果
        assertTrue(added);
    }

    @Test
    void testGetShoppingCartItems() {
        // 先新增一个Book
        Book newBook = new Book();
        newBook.setTitle("title 1");
        newBook.setAuthor("author 1");
        newBook.setPrice(1d);
        newBook.setCategory("cate 1");
        // 调用服务方法
        Long bookId = bookService.addBook(newBook);
        // 假设我们有一个用户ID
        String userId = "xxx";
        // 调用服务方法
        shoppingCartService.addShoppingCart(userId, bookId, 2);

        // 调用服务方法
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartItems(userId);

        // 验证结果
        assertNotNull(shoppingCart);
    }

    @Test
    void testgetTotalPrice() {
        // 先新增一个Book
        Book newBook = new Book();
        newBook.setTitle("title 1");
        newBook.setAuthor("author 1");
        newBook.setPrice(1d);
        newBook.setCategory("cate 1");
        // 调用服务方法
        Long bookId = bookService.addBook(newBook);
        // 假设我们有一个用户ID
        String userId = "xxx";
        // 调用服务方法

        shoppingCartService.addShoppingCart(userId, bookId, 2);
        Double price = shoppingCartService.calculateTotalPrice("xxx");
        assertNotNull(price);
    }
}