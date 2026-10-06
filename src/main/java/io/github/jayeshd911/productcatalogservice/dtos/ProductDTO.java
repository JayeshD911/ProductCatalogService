package io.github.jayeshd911.productcatalogservice.dtos;

import io.github.jayeshd911.productcatalogservice.models.Category;
import io.github.jayeshd911.productcatalogservice.models.Product;
import io.github.jayeshd911.productcatalogservice.models.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private  CategoryDTO category;
    private Double price;
    private String imageUrl;
    private State state;


    public Product convertToProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName()); // null for create; set for update
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImageUrl());
        product.setState(this.getState());
//        if (this.getCategory() != null) {
//            CategoryDTO categoryDTO = new CategoryDTO();
//            categoryDTO.setId(this.getCategory().getId());
//            categoryDTO.setName(this.getCategory().getName());
//            categoryDTO.setDescription(this.getCategory().getDescription());
//            product.setCategory(CategoryDTO.convertToCategory());
//        }
        if (this.getCategory() != null && this.getCategory().getId() != null) {
            Category category = new Category();
            category.setId(this.getCategory().getId());
            product.setCategory(category);
        }

        return product;
    }
}
