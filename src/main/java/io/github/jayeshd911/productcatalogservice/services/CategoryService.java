package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.models.Category;
import io.github.jayeshd911.productcatalogservice.models.State;
import io.github.jayeshd911.productcatalogservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isCategoryExists(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return null;
    }

    @Override
    public Category replaceCategory(Category category, Long id) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setState(State.DELETED);
            categoryRepository.save(existingCategory);
            return true;
        }
        return false;
    }
}
