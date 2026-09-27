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
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }
}
