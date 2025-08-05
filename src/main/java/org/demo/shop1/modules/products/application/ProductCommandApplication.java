package org.demo.shop1.modules.products.application;

import java.util.Calendar;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductCommandException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductCommandOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductCommandService;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
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
    public Mono<Product> createProduct(Product productParam) throws ProductCommandException {
        nameMethod = "createProduct";
        startMethod();

        return productQueryService.findBySku(productParam.getSku())
                // validate unique SKU
                .flatMap(existingProduct -> Mono
                        .<Product>error(new ProductCommandException(ProductMessageEnum.SKU_REPEAT.code,
                                ProductMessageEnum.SKU_REPEAT.message)))
                .switchIfEmpty(Mono.defer(() -> productValidationService.validateBeforeSave(productParam)
                        .flatMap(productValidated -> {

                            logger.info(String.format("Creating product. Name: %s, SKU: %s",
                                    productParam.getName(), productParam.getSku()));

                            // Set current date
                            productValidated.setDateCreated(Calendar.getInstance().getTime());
                            // Set current active product
                            productValidated.setIsActive(true);

                            return productCommandRepository.save(productValidated);
                        })));
    }
}
