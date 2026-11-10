package io.github.jayeshd911.productcatalogservice.controllers;

import io.github.jayeshd911.productcatalogservice.dtos.ProductDTO;
import io.github.jayeshd911.productcatalogservice.models.Product;
import io.github.jayeshd911.productcatalogservice.services.StorageProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
We use @WebMvcTest to test controllers because it loads only the MVC layer,
making tests faster, isolated, and focused on request/response handling.

@SpringBootTest loads the entire application, which is slow and turns
the test into a full integration test instead of a controller unit test.

 */



@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {
    /*
    Test the product controller web apis
     */

    @Autowired
    private MockMvc mockMvc;
    /*
    using mockmvc, we are not actually testing the methods
    rather we are just testing the endpoints, that's it
     */

    @MockitoBean(name = "storageProductService")
    private StorageProductService productService; // Mock the qualified service dependency

    // First create products, then create DTOs and convert them to JSON, then compare the response with the expected JSON
    @Test
     public void testGetAllProducts_RunsSuccessfully() throws Exception {
        // Create a new list of product

//        mockMvc.perform(get("/products")).
//                andExpect(status().isOk());
//        // Here we are just testing the endpoint, not the actual method logic

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("iPhone 14");
        product1.setDescription("Latest Apple iPhone");
        product1.setPrice(999.99);

        List<Product> products = new ArrayList<>();

        products.add(product1);

        // Mock the productService to return the list of products when getAllProducts is called
        when(productService.getAllProducts()).thenReturn(products);


        // Create a new list of product DTOs
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(product1.getId());
        productDTO1.setName(product1.getName());
        productDTO1.setDescription(product1.getDescription());
        productDTO1.setPrice(product1.getPrice());

        List<ProductDTO> productDTOs = new ArrayList<>();
        productDTOs.add(productDTO1);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(productDTOs);

        // Perform the GET request and assert the response
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

     }


}
