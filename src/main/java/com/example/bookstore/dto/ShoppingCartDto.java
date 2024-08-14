package com.example.bookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {

    private Long id;

    private String userId;

    private List<ShoppingCartItemDto> items;

}