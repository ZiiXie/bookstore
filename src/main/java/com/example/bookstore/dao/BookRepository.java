package com.example.bookstore.dao;

import com.example.bookstore.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
