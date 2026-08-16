package io.github.jayeshd911.productcatalogservice.models;

import io.github.jayeshd911.productcatalogservice.dtos.CategoryDTO;
import io.github.jayeshd911.productcatalogservice.dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

    public ProductDTO convert() {
        ProductDTO dto = new ProductDTO();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        dto.setPrice(this.getPrice());
        dto.setImageUrl(this.getImageUrl());
        if (this.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(this.getCategory().getId());
            categoryDTO.setName(this.getCategory().getName());
            categoryDTO.setDescription(this.getCategory().getDescription());
            dto.setCategory(categoryDTO);
        }
        return dto;
    }
}
