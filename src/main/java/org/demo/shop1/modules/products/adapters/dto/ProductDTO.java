package org.demo.shop1.modules.products.adapters.dto;

import org.demo.shop1.modules.products.domain.Product;

import lombok.Data;

@Data
public class ProductDTO implements Product {
    private String sku;

    private String name;

    private Integer categoryId;
}
