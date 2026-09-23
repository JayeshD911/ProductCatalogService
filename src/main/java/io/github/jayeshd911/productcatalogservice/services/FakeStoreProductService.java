package io.github.jayeshd911.productcatalogservice.services;

import io.github.jayeshd911.productcatalogservice.clients.FakeStoreAPIClient;
import io.github.jayeshd911.productcatalogservice.dtos.FakestoreProductDTO;
import io.github.jayeshd911.productcatalogservice.models.Product;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

//    final private RestTemplate restTemplate;
//
//    public FakeStoreProductService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

//    public <T> ResponseEntity<T> putForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<>(request);
//        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType, uriVariables);
//    }

    final private FakeStoreAPIClient fakeStoreAPIClient;

    public FakeStoreProductService(FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }


    @Override
    public Product getProductById(Long id) {
//        // Implement the logic to fetch product by ID from FakeStore API
//        FakestoreProductDTO fakestoreProductDTO =  restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
//                FakestoreProductDTO.class,
//                id);
//
//        return fakestoreProductDTO.from(fakestoreProductDTO);

        ResponseEntity<FakestoreProductDTO> response = fakeStoreAPIClient.getForEntity("https://fakestoreapi.com/products/{id}",
                FakestoreProductDTO.class,
                id);
        if (fakeStoreAPIClient.validateResponse(response)) {

            return response.getBody().convertToProduct();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        // Implement the logic to fetch all products from FakeStore API
        ResponseEntity<FakestoreProductDTO[]> response = fakeStoreAPIClient.getForEntity("https://fakestoreapi.com/products",
                FakestoreProductDTO[].class);
        if (fakeStoreAPIClient.validateResponse(response)){
            FakestoreProductDTO[] fakestoreProductDTOS = response.getBody();

            for(FakestoreProductDTO fakestoreProductDTO : fakestoreProductDTOS){
                products.add(fakestoreProductDTO.convertToProduct());
            }

            return products;
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
        // Implement the logic to create a new product in FakeStore API
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
//        FakestoreProductDTO fakestoreProductDTO = new FakestoreProductDTO();
//        ResponseEntity<FakestoreProductDTO> response = putForEntity("https://fakestoreapi.com/products/{id}",
//                product,
//                FakestoreProductDTO.class,
//                id);

        FakestoreProductDTO fakestoreProductDTO = product.convertTofakestoreProductDTO();

        ResponseEntity<FakestoreProductDTO> response = fakeStoreAPIClient.requestForEntity(
                HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",
                fakestoreProductDTO,
                FakestoreProductDTO.class,
                id
        );

        if (fakeStoreAPIClient.validateResponse(response)) {
            FakestoreProductDTO fakestoreProductDTO1 = response.getBody();
            return fakestoreProductDTO1.convertToProduct();
        }
        return null;
    }
}
