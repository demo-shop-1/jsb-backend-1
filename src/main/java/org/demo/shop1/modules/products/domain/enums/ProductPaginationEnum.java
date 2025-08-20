package org.demo.shop1.modules.products.domain.enums;

public enum ProductPaginationEnum {

    PAGE_INVALID("PAG-1", "Page invalid"),
    SIZE_INVALID("PAG-2", "Size invalid");

    public final String code;
    public final String message;

    private ProductPaginationEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
