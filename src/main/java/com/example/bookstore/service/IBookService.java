package com.example.bookstore.service;

import com.example.bookstore.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBookService {
    Long addBook(Book book);
    Page<Book> getPageBooks(PageRequest pageable);
}
