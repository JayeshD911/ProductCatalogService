package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.models.Product;

import java.util.List;


public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);
}
