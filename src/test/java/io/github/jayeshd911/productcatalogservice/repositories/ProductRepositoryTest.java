package io.github.jayeshd911.productcatalogservice.repositories;

import io.github.jayeshd911.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void testJPAMethods(){
        // Test your JPA methods here

        List<Product> products = productRepository.findProductByPriceBetween(40.0, 100.0);

        System.out.println("Products found between price 40.0 and 100.0: " + products.size());

        String description = productRepository.getDescrioptionWhereId(4L);
        System.out.println("Description of product with ID 4: " + description);
    }

}