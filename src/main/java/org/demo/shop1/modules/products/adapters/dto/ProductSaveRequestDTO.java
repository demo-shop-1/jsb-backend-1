package org.demo.shop1.modules.products.adapters.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSaveRequestDTO extends ProductDTO {

    private String description;

    private Double unitPrice;

    private String imageUrl;

    private Integer unitsInStock;

}
