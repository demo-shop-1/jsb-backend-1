package org.demo.shop1.modules.products.application;

import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductSkuException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.ports.out.ProductQueryOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductQueryApplication implements ProductQueryService {

    private final ProductQueryOutRepository productQueryRepository;

    @Override
    public Mono<Product> findBySku(String sku) {
        return Mono.justOrEmpty(sku).flatMap(s -> {
            if (ObjectUtils.isBlankString(sku)) {
                throw new ProductSkuException(ProductMessageEnum.SKU_BLANK.code, ProductMessageEnum.SKU_BLANK.message);
            }

            return productQueryRepository.findBySku(sku);
        });
    }

}
