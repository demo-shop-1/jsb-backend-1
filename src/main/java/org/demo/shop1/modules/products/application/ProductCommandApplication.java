package org.demo.shop1.modules.products.application;

import java.util.Calendar;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.models.ProductModel;
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
    public Mono<Product> createProduct(ProductModel product) {

        return productQueryService.findBySku(product.getSku())
                .doFirst(() -> startMethod("createProduct"))
                .cast(ProductModel.class)
                // validate unique SKU
                .flatMap(existingProduct -> {
                    infoMethod("createProduct",
                            String.format("Exists a product with this SKU: %s", existingProduct.getSku()));
                    return ProductUtil.throwError(ProductMessageEnum.SKU_REPEATED);
                })
                .switchIfEmpty(Mono.defer(() -> productValidationService.validateBeforeSave(product)
                        .flatMap(productValidated -> productValidationService.validateIfCategoryExist(product))
                        .cast(ProductModel.class)
                        .flatMap(productValidated -> {

                            // Set current date
                            productValidated.setDateCreated(Calendar.getInstance().getTime());
                            // Set current active product
                            productValidated.setIsActive(true);

                            infoMethod("createProduct",
                                    String.format("Creating product with name: %s,  SKU: %s",
                                            productValidated.getName(),
                                            productValidated.getSku()));

                            return productCommandRepository.save(productValidated);
                        })))
                .doOnSuccess(productResult -> endMethod("createProduct"));
    }

    @Override
    public Mono<Product> updateProduct(ProductModel product) {

        return productQueryService.findBySku(product.getSku())
                .doFirst(() -> startMethod("updateProduct"))
                .cast(ProductModel.class)
                .flatMap(existingProduct -> {
                    return productValidationService.validateBeforeUpdate(product)
                            .cast(ProductModel.class)
                            .flatMap(productValidationService::validateIfCategoryExist)
                            .cast(ProductModel.class)
                            .flatMap(productValidated -> {
                                // Update field lastUpdated
                                productValidated.setLastUpdated(Calendar.getInstance().getTime());
                                return productCommandRepository.update(productValidated);
                            });
                })
                .cast(Product.class)
                .switchIfEmpty(Mono.defer(() -> {
                    // validate SKU
                    infoMethod("updateProduct",
                            String.format("There's no a product with this SKU: %s", product.getSku()));
                    return ProductUtil.throwError(ProductMessageEnum.SKU_NOT_EXIST);
                }))
                .doOnSuccess(productResult -> endMethod("updateProduct"));
    }
}
