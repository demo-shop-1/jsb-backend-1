package org.demo.shop1.modules.products.domain.enums;

public enum ProductFieldEnum {
    SKU("sku"),
    NAME("name"),
    CATEGORY_ID("categoryId"),
    DESCRIPTION("description"),
    UNIT_PRICE("unitPrice"),
    IMAGE_URL("imageUrl"),
    IS_ACTIVE("isActive"),
    UNITS_IN_STOCK("unitsInStock"),
    DATE_CREATED("dateCreated"),
    LAST_UPDATED("lastUpdated");

    public final String value;

    private ProductFieldEnum(String value) {
        this.value = value;
    }
}
