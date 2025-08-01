package org.demo.shop1.adapters.web;

import org.demo.shop1.modules.products.adapters.web.ProductController;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

public class ProductControllerTest {

    private WebTestClient webTestClient;

    @Mock
    private ProductCommandService productCommandService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToRouterFunction(productController.productRoute()).build();
    }

    @Test
    void testCreateProduct_Ok() throws JsonProcessingException {

        Product product = new Product();
        product.setSku("BOOK-TECH-1000");

        // Service's Mock
        Mockito.when(productCommandService.createProduct(Mockito.any())).thenReturn(Mono.just(product));

        // Act and Assert
        webTestClient.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ObjectMapper().writeValueAsString(product))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.sku").isEqualTo("BOOK-TECH-1000");
    }

    @Test
    void testCreateProduct_Nok() throws JsonProcessingException {

        Product product = new Product();
        product.setSku("BOOK-TECH-1000");

        // Service's Mock
        Mockito.when(productCommandService.createProduct(Mockito.any()))
                .thenReturn(Mono.error(new ProductSaveException(null, null)));

        // Act and Assert
        webTestClient.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ObjectMapper().writeValueAsString(product))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
