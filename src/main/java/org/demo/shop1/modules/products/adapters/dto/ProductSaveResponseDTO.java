package org.demo.shop1.modules.products.adapters.dto;

import lombok.Data;

@Data
public class ProductSaveResponseDTO {

    private String sku;

    private String name;

    private Integer categoryId;

    private String description;

    private Double unitPrice;

    private String imageUrl;

    private Integer unitsInStock;

    private Boolean isActive;

}
