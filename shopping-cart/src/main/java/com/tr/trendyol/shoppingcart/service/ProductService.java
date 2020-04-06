package com.tr.trendyol.shoppingcart.service;

import com.tr.trendyol.core.service.BaseService;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Service
@Transactional
public class ProductService extends BaseService<Product, Long, ProductRepository> {

    public ProductService(ProductRepository rep) {
        super(rep);
    }
}
