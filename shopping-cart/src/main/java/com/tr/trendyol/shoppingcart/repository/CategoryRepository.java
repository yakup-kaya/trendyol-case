package com.tr.trendyol.shoppingcart.repository;

import com.tr.trendyol.core.repository.BaseRepository;
import com.tr.trendyol.shoppingcart.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    Optional<Category> findOneByTitle(String food);

}
