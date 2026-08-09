package io.github.jayeshd911.productcatalogservice.controllers;

import io.github.jayeshd911.productcatalogservice.dtos.ProductRequestDTO;
import io.github.jayeshd911.productcatalogservice.dtos.ProductResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @PostMapping("/products")
    ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO ProductResponseDTO = new ProductResponseDTO();
        return ProductResponseDTO;
    }

    @GetMapping("/products/{id}")
    ProductResponseDTO getProductById(@PathVariable("id") Long id) {
        ProductResponseDTO ProductResponseDTO = new ProductResponseDTO();
        return ProductResponseDTO;
    }

    @GetMapping("/products")
    List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> products = new ArrayList<>();
        return products;
    }

}
