package com.tr.trendyol.shoppingcart.controller;

import com.tr.trendyol.core.repository.CrudResource;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.repository.CategoryRepository;
import com.tr.trendyol.shoppingcart.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@RestController
@RequestMapping(CategoryController.URL)
public class CategoryController extends CrudResource<Category, Long, CategoryRepository, CategoryService> {

    protected static final String URL = "/api/v1/categories";

    @Override
    protected String getEntityName() {
        return "category";
    }

    @Override
    protected String getBaseUrl() {
        return CategoryController.URL;
    }

}
