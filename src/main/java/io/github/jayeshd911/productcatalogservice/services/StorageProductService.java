package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.models.Category;
import io.github.jayeshd911.productcatalogservice.models.State;
import io.github.jayeshd911.productcatalogservice.repositories.CategoryRepository;
import io.github.jayeshd911.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.jayeshd911.productcatalogservice.models.Product;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("storageProductService")
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getId() != null) {
            throw new IllegalArgumentException("ID should not be provided while creating a product");
        }

        if (categoryRepository.findById(product.getCategory().getId()).isPresent()) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + product.getCategory().getId()));
            product.setCategory(category);
        }

        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setState(State.ACTIVE);
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            //Don't create a new product if it doesn't exist, return null or throw an exception
            return null;
        }

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + product.getCategory().getId()));
            product.setCategory(category);
        }

        product.setId(id);
        product.setCreatedAt(optionalProduct.get().getCreatedAt());
        product.setUpdatedAt(new Date());

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getState().equals(State.ACTIVE)) {
                product.setState(State.DELETED);
                productRepository.save(product);
            }
            else {
                throw new IllegalStateException("Product is already deleted");
            }
            return true;
        } else {
            return false;
        }
    }
}
