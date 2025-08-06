package org.demo.shop1.modules.products.application;

import java.util.Calendar;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductCommandException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.modules.products.domain.utils.ProductUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductCommandApplication extends ProductApplication implements ProductCommandService {

    private final ProductCommandOutRepository productCommandRepository;
    private final ProductQueryService productQueryService;
    private final ProductValidationService productValidationService;

    @PostConstruct
    public void init() {
        nameClass = "ProductCommandApplication";
    }

    @Override
    public Mono<Product> createProduct(Product product) throws ProductCommandException {

        return productQueryService.findBySku(product.getSku())
                .doFirst(() -> startMethod("createProduct"))
                // validate unique SKU
                .flatMap(existingProduct -> {
                    infoMethod("createProduct",
                            String.format("Exists a product with this SKU: %s", existingProduct.getSku()));
                    return ProductUtil.error(ProductMessageEnum.SKU_REPEATED);
                })
                .switchIfEmpty(Mono.defer(() -> productValidationService.validateBeforeSave(product)
                        .flatMap(productValidated -> productValidationService.validateIfCategoryExist(product))
                        .flatMap(productValidated -> {
                            // Set current date
                            product.setDateCreated(Calendar.getInstance().getTime());
                            // Set current active product
                            product.setIsActive(true);

                            infoMethod("createProduct", String.format("Creating product with name: %s, SKU: %s",
                                    product.getName(), product.getSku()));

                            return productCommandRepository.save(product);
                        })))
                .doOnSuccess(productResult -> endMethod("createProduct"));
    }
}
