package com.tr.trendyol.shoppingcart.controller;

import com.tr.trendyol.core.repository.CrudResource;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.repository.ProductRepository;
import com.tr.trendyol.shoppingcart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@RestController
@RequestMapping(ProductController.URL)
public class ProductController extends CrudResource<Product, Long, ProductRepository, ProductService> {

    protected static final String URL = "/api/v1/products";

    @Override
    protected String getEntityName() {
        return "product";
    }

    @Override
    protected String getBaseUrl() {
        return ProductController.URL;
    }

}
