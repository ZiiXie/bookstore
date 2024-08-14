package com.example.bookstore;

import com.example.bookstore.bean.Book;
import com.example.bookstore.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BookServiceTest {

    @Autowired
    private IBookService bookService;

    @Test
    void testAddBook() {
        // 创建一个新的书籍对象
        Book newBook = new Book();
        newBook.setTitle("title 1");
        newBook.setAuthor("author 1");
        newBook.setPrice(1d);
        newBook.setCategory("cate 1");
        // 调用服务方法
        Long bookId = bookService.addBook(newBook);
        // 验证结果
        assertNotNull(bookId);
    }

    @Test
    void testGetPageBooks() {
        List<Book> books = new ArrayList<>();
        Book newBook1 = new Book();
        newBook1.setTitle("Title 1");
        newBook1.setAuthor("Author 1");
        newBook1.setPrice(1.99);
        newBook1.setCategory("Category 1");
        books.add(newBook1);
        bookService.addBook(newBook1);
        Book newBook2 = new Book();
        newBook2.setTitle("Title 2");
        newBook2.setAuthor("Author 2");
        newBook2.setPrice(2.99);
        newBook2.setCategory("Category 2");
        books.add(newBook2);
        bookService.addBook(newBook2);
        Page<Book> expectedPage = new PageImpl<>(books);

        int page = 0;
        int size = 10;
        PageRequest pageable = PageRequest.of(page, size);
        // 调用服务方法
        Page<Book> resultPage = bookService.getPageBooks(pageable);
        // 验证结果
        assertNotNull(resultPage.getTotalElements());
        assertEquals(expectedPage.getTotalElements(), resultPage.getTotalElements());
        assertEquals(expectedPage.getTotalPages(), resultPage.getTotalPages());
    }
}