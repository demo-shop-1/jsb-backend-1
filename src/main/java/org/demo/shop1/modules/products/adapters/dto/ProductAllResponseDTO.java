package org.demo.shop1.modules.products.adapters.dto;

import java.util.List;

import org.demo.shop1.modules.products.domain.Product;

import lombok.Data;

@Data
public class ProductAllResponseDTO implements Product {
    private List<ProductSingleResponseDTO> content;
    private int page;
    private int size;
    private long totalElements;
}
