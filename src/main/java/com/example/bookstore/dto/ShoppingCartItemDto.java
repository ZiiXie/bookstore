package com.example.bookstore.dto;

import com.example.bookstore.bean.Book;
import lombok.Data;

@Data
public class ShoppingCartItemDto {

    private Long id;

    private Book book;

    private Integer quantity;

}