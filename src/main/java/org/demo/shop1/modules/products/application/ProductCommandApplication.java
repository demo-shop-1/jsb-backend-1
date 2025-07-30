package org.demo.shop1.modules.products.application;

import java.util.Calendar;
import java.util.logging.Logger;

import org.demo.shop1.modules.products.domain.exceptions.ProductException;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductCommandApplication implements ProductCommandService {

    protected static final Logger logger = Logger.getLogger(ProductCommandApplication.class.getName());

    private final ProductCommandOutRepository productCommandRepository;

    private final ProductQueryService productQueryService;

    @Override
    public Mono<Product> createProduct(Product product) throws ProductException {

        logger.info(String.format("Creating product. Name: %s, SKU: %s", product.getName(), product.getSku()));

        // Validate unique SKU
        return productQueryService.findBySku(product.getSku())
                .doOnNext(result -> {
                    // validate if product exists or not
                    if (result != null) {
                        throw new ProductSaveException(12, "SKU has been repeated");
                    }
                })
                .switchIfEmpty(Mono.just(product).flatMap(p -> {
                    // Set current date
                    product.setDateCreated(Calendar.getInstance().getTime());
                    // Set current active product
                    product.setIsActive(true);
                    return productCommandRepository.save(product);
                }));
    }
}
