package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.models.Category;

import java.util.List;

public interface ICategoryService {

    boolean isCategoryExists(Long categoryId);

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(Category category, Long categoryId);

    Category replaceCategory(Category category, Long id);

    boolean deleteCategory(Long categoryId);

}
