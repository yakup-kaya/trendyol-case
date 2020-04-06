package com.tr.trendyol.shoppingcart.controller;

import com.tr.trendyol.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(ShoppingCartController.URL)
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    protected static final String URL = "/api/v1/shoppingcart";

    //TODO: Should be implemented all rest methods for UI


}
