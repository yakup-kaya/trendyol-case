package com.tr.trendyol.shoppingcart.service;

import com.tr.trendyol.core.service.BaseService;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Service
@Transactional
public class CategoryService extends BaseService<Category, Long, CategoryRepository> {

    public CategoryService(CategoryRepository rep) {
        super(rep);
    }

    public Category findOneByTitle(String name) {
        return repository.findOneByTitle(name).orElseThrow(EntityNotFoundException::new);
    }
}
