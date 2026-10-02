package io.github.jayeshd911.productcatalogservice.controllers;

import io.github.jayeshd911.productcatalogservice.dtos.CategoryDTO;
import io.github.jayeshd911.productcatalogservice.models.Category;
import io.github.jayeshd911.productcatalogservice.models.State;
import io.github.jayeshd911.productcatalogservice.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setState(State.ACTIVE);

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO response = new CategoryDTO();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        response.setDescription(savedCategory.getDescription());
        return response;
    }

}
