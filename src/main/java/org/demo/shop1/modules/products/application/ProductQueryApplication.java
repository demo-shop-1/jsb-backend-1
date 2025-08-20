package org.demo.shop1.modules.products.application;

import java.util.Optional;

import org.demo.shop1.modules.products.application.dto.ProductAllAppDTO;
import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.enums.ProductFieldEnum;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.ports.out.ProductQueryOutRepository;
import org.demo.shop1.modules.products.domain.services.ProductQueryService;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.modules.products.domain.utils.ProductUtil;
import org.demo.shop1.utils.ObjectUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductQueryApplication extends ProductApplication implements ProductQueryService {

    private final ProductQueryOutRepository productQueryRepository;
    private final ProductValidationService productValidationService;

    @PostConstruct
    public void init() {
        nameClass = "ProductQueryApplication";
    }

    @Override
    public Mono<Product> findBySku(String sku) {
        return Mono.defer(() -> (ObjectUtil.isBlankString(sku))
                ? ProductUtil.throwQueryError(ProductMessageEnum.SKU_BLANK)
                : productQueryRepository.findBySku(sku))
                .cast(Product.class)
                .doFirst(() -> startMethod("findBySku"))
                .doOnSuccess(category -> endMethod("findBySku"));
    }

    @Override
    public Mono<Product> findOne(String sku) {
        return findBySku(sku)
                .doFirst(() -> startMethod("findOne"))
                .switchIfEmpty(ProductUtil.throwQueryError(ProductMessageEnum.PRODUCT_NOT_EXIST))
                .doOnSuccess(category -> endMethod("findOne"));
    }

    @Override
    public Mono<Product> findAll(Optional<String> page, Optional<String> size, Optional<String> categoryFilter) {
        return productValidationService.validatePagination(page, size)
                .doFirst(() -> startMethod("findAll"))
                .switchIfEmpty(Mono.defer(() -> {
                    Integer pageGet = Integer.parseInt(page.get());
                    Integer sizeGet = Integer.parseInt(size.get());

                    Pageable pageable = PageRequest.of(pageGet, sizeGet);
                    Query query = new Query().with(pageable);

                    if (categoryFilter.isPresent()) {
                        query.addCriteria(Criteria.where(ProductFieldEnum.CATEGORY_ID.value)
                                .is(Integer.parseInt(categoryFilter.get())));
                    }
                    infoMethod("findAll", String.format("Page request. Page: %d, Size: %d, CategoryId: %s",
                            pageGet, sizeGet, categoryFilter.orElse("none")));

                    return productQueryRepository.findAll(query)
                            .flatMap(tuple -> {
                                ProductAllAppDTO dto = new ProductAllAppDTO();
                                dto.setContent(tuple.getT1());
                                dto.setPage(pageGet);
                                dto.setSize(sizeGet);
                                dto.setTotalElements(tuple.getT2());

                                return Mono.just(dto);
                            });
                }))
                .doOnSuccess(result -> endMethod("findAll"));
    }

}
