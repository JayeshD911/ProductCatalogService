package io.github.jayeshd911.productcatalogservice.dtos;

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
}
