package com.tr.trendyol.shoppingcart.repository;

import com.tr.trendyol.core.repository.BaseRepository;
import com.tr.trendyol.shoppingcart.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

}
