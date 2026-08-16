package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.dtos.FakestoreProductDTO;
import io.github.jayeshd911.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private RestTemplate restTemplate;

    private FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
//        // Implement the logic to fetch product by ID from FakeStore API
//        FakestoreProductDTO fakestoreProductDTO =  restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
//                FakestoreProductDTO.class,
//                id);
//
//        return fakestoreProductDTO.from(fakestoreProductDTO);


        ResponseEntity<FakestoreProductDTO> fakestoreDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakestoreProductDTO.class,
                id);
        if (fakestoreDTOResponseEntity.hasBody() &&
                fakestoreDTOResponseEntity.getStatusCode().equals(
                        HttpStatusCode.valueOf(200))) {
            return fakestoreDTOResponseEntity.getBody().from();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        // Implement the logic to fetch all products from FakeStore API
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        // Implement the logic to create a new product in FakeStore API
        return null;
    }
}
