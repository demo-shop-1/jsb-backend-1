package org.demo.shop1.modules.products.domain.enums;

public enum ProductIntegerEnum {
    SKU_MIN_SIZE(4),
    UNIT_PRICE_MIN(0),
    UNIT_IN_STOCK_MIN(0);

    public final int value;

    private ProductIntegerEnum(int value) {
        this.value = value;
    }
}
