package org.demo.shop1.modules.products.application;

import java.util.Calendar;
import java.util.logging.Logger;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductSaveException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductCommandApplication implements ProductCommandService {

    protected static final Logger logger = Logger.getLogger(ProductCommandApplication.class.getName());
    private final ProductCommandOutRepository productCommandRepository;
    private final ProductQueryService productQueryService;
    private final ProductValidationService productValidationService;

    @Override
    public Mono<Product> createProduct(Product productParam) throws ProductSaveException {

        logger.info(
                String.format("Creating product. Name: %s, SKU: %s", productParam.getName(), productParam.getSku()));

        return productQueryService.findBySku(productParam.getSku())
                .doOnNext(result -> {
                    // validate unique SKU
                    if (result != null) {
                        throw new ProductSaveException(ProductMessageEnum.SKU_REPEAT.code,
                                ProductMessageEnum.SKU_REPEAT.message);
                    }
                })
                .switchIfEmpty(Mono.just(productParam)
                        .flatMap(productValidationService::validateBeforeSave)
                        .flatMap(productValidated -> {

                            // Set current date
                            productValidated.setDateCreated(Calendar.getInstance().getTime());
                            // Set current active product
                            productValidated.setIsActive(true);

                            return productCommandRepository.save(productValidated);
                        }));
    }
}
