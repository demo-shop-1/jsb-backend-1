package org.demo.shop1.adapters.web;

import org.demo.shop1.modules.products.adapters.web.ProductCommandController;
import org.demo.shop1.modules.products.domain.exceptions.ProductCommandException;
import org.demo.shop1.modules.products.domain.models.ProductModel;
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
    private ProductCommandController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToRouterFunction(productController.productCommandRoutes()).build();
    }

    @Test
    void testCreateProduct_Ok() throws JsonProcessingException {

        ProductModel product = new ProductModel();
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

        ProductModel product = new ProductModel();
        product.setSku("BOOK-TECH-1000");

        // Service's Mock
        Mockito.when(productCommandService.createProduct(Mockito.any()))
                .thenReturn(Mono.error(new ProductCommandException(null, null)));

        // Act and Assert
        webTestClient.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ObjectMapper().writeValueAsString(product))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
