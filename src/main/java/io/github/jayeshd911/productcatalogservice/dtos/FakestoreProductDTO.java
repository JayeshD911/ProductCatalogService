package io.github.jayeshd911.productcatalogservice.dtos;

import io.github.jayeshd911.productcatalogservice.models.Category;
import io.github.jayeshd911.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product from () {
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImage());
        // In fakestore API, category is a string. We need to map it to our Category entity as needed.
        Category category = new Category();
        category.setName(this.getCategory());
        product.setCategory(category); // Category mapping can be handled separately if needed
        return product;
    }
}

//[
//        {
//        "id": 0,
//        "title": "string",
//        "price": 0.1,
//        "description": "string",
//        "category": "string",
//        "image": "http://example.com"
//        }
//        ]