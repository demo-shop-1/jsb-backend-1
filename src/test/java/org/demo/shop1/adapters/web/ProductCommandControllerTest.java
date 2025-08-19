package org.demo.shop1.adapters.web;

import java.util.List;

import org.demo.shop1.modules.products.adapters.dto.ProductDeleteResponseDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductSaveResponseDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductUpdateRequestDTO;
import org.demo.shop1.modules.products.adapters.dto.ProductUpdateResponseDTO;
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

public class ProductCommandControllerTest {

    private WebTestClient webTestClientCommand;

    @Mock
    private ProductCommandService productCommandApplication;

    @InjectMocks
    private ProductCommandController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClientCommand = WebTestClient.bindToRouterFunction(productController.productCommandRoutes()).build();
    }

    @Test
    void testDeleteProduct_Nok() {

        // Service's Mock
        Mockito.when(productCommandApplication.deleteProduct(Mockito.any(String.class)))
                .thenReturn(Mono.error(new ProductCommandException(null, null)));

        // Act and Assert
        webTestClientCommand.delete()
                .uri("/product/delete/{id}", "1")
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testDeleteProduct_Ok() {
        // Arrange
        ProductModel data = new ProductModel();
        ProductDeleteResponseDTO response = new ProductDeleteResponseDTO();

        // App's slayer mock
        Mockito.when(productCommandApplication.deleteProduct(Mockito.any(String.class)))
                .thenReturn(Mono.just(data));

        // Act and Assert
        webTestClientCommand.delete()
                .uri("/product/delete/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductDeleteResponseDTO.class)
                .isEqualTo(response);
    }

    @Test
    void testUpdateProduct_Nok() {
        // Arrange
        ProductUpdateRequestDTO request = new ProductUpdateRequestDTO();
        request.setSku("BOOK-TECH-1005");

        // Service's Mock
        Mockito.when(productCommandApplication.createProduct(Mockito.any()))
                .thenReturn(Mono.error(new ProductCommandException(null, null)));

        // Act and Assert
        webTestClientCommand.put()
                .uri("/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUpdateProduct_Ok() {

        // Arrange
        ProductUpdateRequestDTO request = new ProductUpdateRequestDTO();
        request.setSku("BOOK-TECH-1005");

        ProductModel data = new ProductModel();
        data.setSku("BOOK-TECH-1005");

        ProductUpdateResponseDTO response = new ProductUpdateResponseDTO();
        response.setSku("BOOK-TECH-1005");

        // App's slayer mock
        Mockito.when(productCommandApplication.updateProduct(Mockito.any(ProductModel.class)))
                .thenReturn(Mono.just(data));

        // Act and Assert
        webTestClientCommand.put()
                .uri("/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductUpdateResponseDTO.class)
                .isEqualTo(response);

    }

    @Test
    void testCreateAllProduct_Nok() throws JsonProcessingException {

        ProductModel product = new ProductModel();
        product.setSku("BOOK-TECH-1000");

        // Service's Mock
        Mockito.when(productCommandApplication.createProduct(Mockito.any()))
                .thenReturn(Mono.error(new ProductCommandException(null, null)));

        // Act and Assert
        webTestClientCommand.post()
                .uri("/product/save/all")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testCreateAllProduct_ok() throws JsonProcessingException {

        ProductModel product1 = new ProductModel();
        ProductModel product2 = new ProductModel();
        product1.setSku("BOOK-TECH-1000");
        product2.setSku("BOOK-TECH-1001");

        // Service's Mock
        Mockito.when(productCommandApplication.createProduct(Mockito.any())).thenReturn(Mono.just(product1));

        // Act and Assert
        webTestClientCommand.post()
                .uri("/product/save/all")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(List.of(product1, product2))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductSaveResponseDTO.class)
                .hasSize(2);
    }

    @Test
    void testCreateProduct_Ok() throws JsonProcessingException {

        ProductModel product = new ProductModel();
        product.setSku("BOOK-TECH-1000");

        // Service's Mock
        Mockito.when(productCommandApplication.createProduct(Mockito.any())).thenReturn(Mono.just(product));

        // Act and Assert
        webTestClientCommand.post()
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
        Mockito.when(productCommandApplication.createProduct(Mockito.any()))
                .thenReturn(Mono.error(new ProductCommandException(null, null)));

        // Act and Assert
        webTestClientCommand.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ObjectMapper().writeValueAsString(product))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
