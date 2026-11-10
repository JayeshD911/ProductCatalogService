package io.github.jayeshd911.productcatalogservice.controllers;

import io.github.jayeshd911.productcatalogservice.dtos.ProductDTO;
import io.github.jayeshd911.productcatalogservice.models.Product;
import io.github.jayeshd911.productcatalogservice.services.StorageProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockitoBean
    private StorageProductService  productService; //Testing specific service implementation

    @Test
    public void testGetProductById_WithValidId_RunsSuccessfully() {

        /*
        Arrange Step
        */
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone 14");
        product.setDescription("Latest Apple iPhone lol");
        product.setPrice(999.99);

        // Mock the productService to return the product when getProductById is called
        when(productService.getProductById(1L)).thenReturn(product);

        /*
        Act Step
        */
        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        /*
        Assert Step
        */
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(product.getId(), response.getBody().getId());
        assertEquals(product.getName(), response.getBody().getName());
        assertEquals(product.getDescription(), response.getBody().getDescription());
        assertEquals(product.getPrice(), response.getBody().getPrice());

        verify(productService, times(1)).getProductById(1L);
    }

    /*
    Sad scenario
    assertThrows(Exception.class, () -> {
        // Code that is expected to throw the exception
    });

     */

    @Test
    public void testGetProductById_WithInvalidId_ThrowsIllegalArgumentException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductById(-1L);
        });

        verify(productService, times(0)).getProductById(1L);

        String expectedMessage = "Invalid product ID: -1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

/*
MockMvc tests are a way to test your Spring MVC controllers
without starting the full server.

They simulate HTTP requests (GET, POST, PUT…) and let you test:
- Controller endpoints
- Request validation
- Request/response structure
- HTTP status codes
- JSON serialization/deserialization
- Interactions with services (via mocks)


They are fast, lightweight, and focused.

 */