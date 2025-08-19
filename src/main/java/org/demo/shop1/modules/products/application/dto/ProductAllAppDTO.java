package org.demo.shop1.modules.products.application.dto;

import java.util.List;

import org.demo.shop1.modules.products.domain.Product;
import org.demo.shop1.modules.products.domain.models.ProductModel;

import lombok.Data;

@Data
public class ProductAllAppDTO implements Product {
    private List<ProductModel> content;
    private int page;
    private int size;
    private long totalElements;
}
