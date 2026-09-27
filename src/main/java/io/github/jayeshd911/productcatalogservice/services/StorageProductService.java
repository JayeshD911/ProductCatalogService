package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.jayeshd911.productcatalogservice.models.Product;
import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isEmpty()) {
            return productRepository.save(product);
        } else {
            // we can throw an exception that product already exists
            return null;
        }
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            //Don't create a new product if it doesn't exist, return null or throw an exception
            return null;
        } else {
            product.setId(id);
            product.setCreatedAt(optionalProduct.get().getCreatedAt()); // Preserve the original createdAt timestamp
            product.setUpdatedAt(new java.util.Date()); // Update the updatedAt timestamp

            return productRepository.save(product);

        }
    }
}
