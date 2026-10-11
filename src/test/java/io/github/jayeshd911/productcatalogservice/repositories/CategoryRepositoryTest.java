package io.github.jayeshd911.productcatalogservice.repositories;

import io.github.jayeshd911.productcatalogservice.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void testFetchTypes() {
        /*
            1. LAZY
            2. EAGER
         */

        Optional<Category> OptionalCategory = categoryRepository.findById(2L);

        if(OptionalCategory.isPresent()){
            Category category = OptionalCategory.get();
            System.out.println("Category Name: " + category.getName());
            System.out.println("Products: " + category.getProducts());
        } else {
            System.out.println("Category not found");
        }
    }

    @Test
    @Transactional
    public void NPlusOneProblemTest() {
        // This test is to demonstrate the N+1 problem
        // Fetch all categories and their products
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            System.out.println("Category: " + category.getName());
            System.out.println("Products: " + category.getProducts());
        }
    }

}