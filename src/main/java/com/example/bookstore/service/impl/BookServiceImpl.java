package com.example.bookstore.service.impl;

import com.example.bookstore.bean.Book;
import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long addBook(Book book) {
        book = bookRepository.save(book);
        return book.getId();
    }

    @Override
    public Page<Book> getPageBooks(PageRequest pageable) {
        return bookRepository.findAll(pageable);
    }

}
