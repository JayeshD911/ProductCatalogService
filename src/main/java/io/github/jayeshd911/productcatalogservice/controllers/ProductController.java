package io.github.jayeshd911.productcatalogservice.controllers;

import io.github.jayeshd911.productcatalogservice.dtos.ProductDTO;
import io.github.jayeshd911.productcatalogservice.models.Product;
import io.github.jayeshd911.productcatalogservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    IProductService productService;

    // Constructor injection
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO productRequestDTO) {
        ProductDTO ProductResponseDTO = new ProductDTO();

        return ProductResponseDTO;
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {

        if(id == null || id <= 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProductDTO productDTO = new ProductDTO();
        Product product = productService.getProductById(id);

        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productDTO = product.convert();

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/products")
    List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        return products;
    }

}
