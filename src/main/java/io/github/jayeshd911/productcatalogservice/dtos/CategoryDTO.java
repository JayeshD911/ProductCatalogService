package io.github.jayeshd911.productcatalogservice.dtos;

import io.github.jayeshd911.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO  {
    private Long id;
    private String name;
    private String description;

    public static Category convertToCategory() {
        Category category = new Category();
        category.setId(null);
        category.setName(null);
        category.setDescription(null);
        return new Category();
    }
}
