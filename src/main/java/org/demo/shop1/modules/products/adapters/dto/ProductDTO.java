package org.demo.shop1.modules.products.adapters.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String sku;

    private String name;

    private Integer categoryId;
}
