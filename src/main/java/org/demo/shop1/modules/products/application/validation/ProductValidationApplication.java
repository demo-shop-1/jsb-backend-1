package org.demo.shop1.modules.products.application.validation;

import org.demo.shop1.modules.products.application.ProductApplication;
import org.demo.shop1.modules.products.domain.enums.ProductIntegerEnum;
import org.demo.shop1.modules.products.domain.enums.ProductMessageEnum;
import org.demo.shop1.modules.products.domain.exceptions.ProductValidationException;
import org.demo.shop1.modules.products.domain.models.Product;
import org.demo.shop1.modules.products.domain.services.ProductValidationService;
import org.demo.shop1.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductValidationApplication extends ProductApplication implements ProductValidationService {

    @PostConstruct
    public void init() {
        nameClass = "ProductValidationApplication";
    }

    @Override
    public Mono<Product> validateBeforeSave(Product product) throws ProductValidationException {
        return Mono.defer(() -> {
            startMethod("validateBeforeSave");
            Mono<Product> result = null;
            
            // validate SKU
            if (ObjectUtils.isBlankString(product.getSku())) {
                result = error(ProductMessageEnum.SKU_BLANK);
            }
            if (product.getSku().length() < ProductIntegerEnum.SKU_MIN_SIZE.value) {
                result = error(ProductMessageEnum.SKU_MIN);
            }

            // validate name
            if (ObjectUtils.isBlankString(product.getName())) {
                result = error(ProductMessageEnum.NAME_BLANK);
            }

            // validate category
            if (product.getCategoryId() == null) {
                result = error(ProductMessageEnum.CATEGORY_NULL);
            }

            // validate description
            if (ObjectUtils.isBlankString(product.getDescription())) {
                result = error(ProductMessageEnum.DESCRIPTION_BLANK);
            }

            // validate unit price
            if (product.getUnitPrice() == null) {
                result = error(ProductMessageEnum.UNIT_PRICE_NULL);
            }
            if (product.getUnitPrice() < ProductIntegerEnum.UNIT_PRICE_MIN.value) {
                result = error(ProductMessageEnum.UNIT_PRICE_MIN);
            }

            // validate units in stock
            if (product.getUnitsInStock() == null) {
                result = error(ProductMessageEnum.UNIT_IN_STOCK_NULL);
            }
            if (product.getUnitsInStock() < ProductIntegerEnum.UNIT_IN_STOCK_MIN.value) {
                result = error(ProductMessageEnum.UNIT_IN_STOCK_MIN);
            }

            if (result == null) {
                result = Mono.just(product);
            }

            endMethod("validateBeforeSave");

            return result;
        });
    }

    private Mono<Product> error(ProductMessageEnum message) {
        return Mono.error(new ProductValidationException(message.code, message.message));
    }

}
