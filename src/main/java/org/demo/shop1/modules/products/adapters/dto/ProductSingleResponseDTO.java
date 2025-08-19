package org.demo.shop1.modules.products.adapters.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ProductSingleResponseDTO extends ProductDTO {

    private String description;

    private Double unitPrice;

    private String imageUrl;

    private Integer unitsInStock;

    private Boolean isActive;
}
