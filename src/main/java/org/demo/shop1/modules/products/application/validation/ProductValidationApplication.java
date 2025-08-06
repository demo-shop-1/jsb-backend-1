package org.demo.shop1.modules.products.application.validation;

import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.demo.shop1.modules.products.application.ProductApplication;
import org.demo.shop1.modules.products.domain.enums.ProductIntegerEnum;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductValidationException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.modules.products.domain.utils.ProductUtil;
import org.demo.shop1.utils.ObjectUtil;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductValidationApplication extends ProductApplication implements ProductValidationService {

    private final CategoryQueryService categoryQueryApplication;

    @PostConstruct
    public void init() {
        nameClass = "ProductValidationApplication";
    }

    @Override
    public Mono<Product> validateBeforeSave(Product product) throws ProductValidationException {
        return Mono.defer(() -> {
            Mono<Product> result = null;

            // validate SKU
            if (ObjectUtil.isBlankString(product.getSku())) {
                result = ProductUtil.error(ProductMessageEnum.SKU_BLANK);
            }
            if (product.getSku().length() < ProductIntegerEnum.SKU_MIN_SIZE.value) {
                result = ProductUtil.error(ProductMessageEnum.SKU_MIN);
            }

            // validate name
            if (ObjectUtil.isBlankString(product.getName())) {
                result = ProductUtil.error(ProductMessageEnum.NAME_BLANK);
            }

            // validate category
            if (product.getCategoryId() == null) {
                result = ProductUtil.error(ProductMessageEnum.CATEGORY_NULL);
            }

            // validate description
            if (ObjectUtil.isBlankString(product.getDescription())) {
                result = ProductUtil.error(ProductMessageEnum.DESCRIPTION_BLANK);
            }

            // validate unit price
            if (product.getUnitPrice() == null) {
                result = ProductUtil.error(ProductMessageEnum.UNIT_PRICE_NULL);
            }
            if (product.getUnitPrice() < ProductIntegerEnum.UNIT_PRICE_MIN.value) {
                result = ProductUtil.error(ProductMessageEnum.UNIT_PRICE_MIN);
            }

            // validate units in stock
            if (product.getUnitsInStock() == null) {
                result = ProductUtil.error(ProductMessageEnum.UNIT_IN_STOCK_NULL);
            }
            if (product.getUnitsInStock() < ProductIntegerEnum.UNIT_IN_STOCK_MIN.value) {
                result = ProductUtil.error(ProductMessageEnum.UNIT_IN_STOCK_MIN);
            }

            if (result == null) {
                result = Mono.just(product);
            }

            return result;
        })
                .doFirst(() -> startMethod("validateBeforeSave"))
                .doOnSuccess(result -> endMethod("validateBeforeSave"));
    }

    @Override
    public Mono<Boolean> validateIfCategoryExist(Product product) {
        return categoryQueryApplication.findById(product.getCategoryId())
                .flatMap(existingCategory -> {
                    return Mono.just(existingCategory != null);
                })
                .doFirst(() -> startMethod("validateIfCategoryExist"))
                .doOnSuccess(categoryResult -> endMethod("validateIfCategoryExist"));

    }
}
