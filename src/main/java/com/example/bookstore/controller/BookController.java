package com.example.bookstore.controller;

import com.example.bookstore.annotation.ResponseResultBody;
import com.example.bookstore.bean.Book;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "books")
@RestController
@RequestMapping("/api/books")
@ResponseResultBody
public class BookController {

    @Autowired
    private IBookService bookService;

    @Operation(summary  = "add book")
    @PostMapping
    public Long addBook(@RequestBody BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        return bookService.addBook(book);
    }

    @Operation(summary  = "get page books")
    @GetMapping
    public Page<Book> getPageBooks(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return bookService.getPageBooks(pageable);
    }

}
