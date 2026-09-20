package io.github.jayeshd911.productcatalogservice.dtos;

import io.github.jayeshd911.productcatalogservice.models.Product;
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


    public Product convertToProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImageUrl());
        if (this.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(this.getCategory().getId());
            categoryDTO.setName(this.getCategory().getName());
            categoryDTO.setDescription(this.getCategory().getDescription());
            product.setCategory(CategoryDTO.convert());
        }
        return product;
    }
}
