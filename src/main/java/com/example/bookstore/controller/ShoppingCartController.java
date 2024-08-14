package com.example.bookstore.controller;

import com.example.bookstore.annotation.ResponseResultBody;
import com.example.bookstore.annotation.ResultData;
import com.example.bookstore.bean.ShoppingCart;
import com.example.bookstore.bean.ShoppingCartItem;
import com.example.bookstore.dto.ShoppingCartDto;
import com.example.bookstore.dto.ShoppingCartItemDto;
import com.example.bookstore.service.IShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "shopping carts")
@RestController
@RequestMapping("/api/shopping-carts")
@ResponseResultBody
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Operation(summary  = "add shopping cart")
    @PostMapping
    public Boolean addShoppingCart(String userId, Long bookId, int quantity) {
        return shoppingCartService.addShoppingCart(userId, bookId, quantity);
    }

    @Operation(summary  = "get shopping cart items by user")
    @GetMapping("/{userId}")
    public ResultData<ShoppingCartDto> getShoppingCartItems(@PathVariable String userId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartItems(userId);
        ShoppingCartDto shoppingCartDto = null;
        if (shoppingCart != null) {
            shoppingCartDto = new ShoppingCartDto();
            BeanUtils.copyProperties(shoppingCart, shoppingCartDto);
            List<ShoppingCartItemDto> shoppingCartItemDtoList = new ArrayList<>();
            for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
                ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
                BeanUtils.copyProperties(shoppingCartItem, shoppingCartItemDto);
                shoppingCartItemDtoList.add(shoppingCartItemDto);
            }
            shoppingCartDto.setItems(shoppingCartItemDtoList);
        }
        return ResultData.success(shoppingCartDto);
    }

    @Operation(summary  = "get shopping cart total price by user")
    @GetMapping("/{userId}/total-price")
    public ResultData<Double> getTotalPrice(@PathVariable String userId) {
        Double totalPrice = shoppingCartService.calculateTotalPrice(userId);
        return ResultData.success(totalPrice);
    }
}