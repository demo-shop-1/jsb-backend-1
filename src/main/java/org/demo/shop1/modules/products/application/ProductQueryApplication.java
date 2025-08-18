package org.demo.shop1.modules.products.application;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductQueryException;
import org.demo.shop1.modules.products.domain.ports.out.ProductQueryOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.utils.ObjectUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductQueryApplication extends ProductApplication implements ProductQueryService {

    private final ProductQueryOutRepository productQueryRepository;

    @PostConstruct
    public void init() {
        nameClass = "ProductQueryApplication";
    }

    @Override
    public Mono<Product> findBySku(String sku) {
        return Mono.defer(() -> (ObjectUtil.isBlankString(sku))
                ? Mono.error(new ProductQueryException(ProductMessageEnum.SKU_BLANK.code,
                        ProductMessageEnum.SKU_BLANK.message))
                : productQueryRepository.findBySku(sku))
                .cast(Product.class)
                .doFirst(() -> startMethod("findBySku"))
                .doOnSuccess(category -> endMethod("findBySku"));
    }

}
