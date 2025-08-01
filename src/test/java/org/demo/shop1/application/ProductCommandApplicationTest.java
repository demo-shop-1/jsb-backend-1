package org.demo.shop1.application;

import org.demo.shop1.modules.products.application.ProductCommandApplication;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductCommandApplicationTest {

    @Mock
    ProductQueryService productQueryService;

    @Mock
    ProductCommandOutRepository productCommandOutRepository;

    @Mock
    ProductValidationService productValidationService;

    @InjectMocks
    ProductCommandApplication productCommandApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_Ok() throws ProductSaveException {
        // Arrange
        Product product = new Product();
        product.setSku("BOOK");

        // Service's Mock
        Mockito.when(productQueryService.findBySku(Mockito.any())).thenReturn(Mono.empty());
        Mockito.when(productValidationService.validateBeforeSave(Mockito.any())).thenReturn(Mono.just(product));
        Mockito.when(productCommandOutRepository.save(Mockito.any())).thenReturn(Mono.just(product));

        // Act and Assert
        StepVerifier.create(productCommandApplication.createProduct(product))
                .expectNextMatches(result -> result.getSku().equals("BOOK"))
                .verifyComplete();
    }

    @Test
    void testCreateProduct_Nok() throws ProductSaveException {
        // Arrange
        Product product = new Product();
        product.setSku("BOOK");

        Mockito.when(productQueryService.findBySku(Mockito.any())).thenReturn(Mono.just(product));

        // Act and Assert
        StepVerifier.create(productCommandApplication.createProduct(product))
                .expectErrorMatches(result -> result instanceof ProductSaveException
                        && result.getMessage().contains(ProductMessageEnum.SKU_REPEAT.message))
                .verify();
    }
}
