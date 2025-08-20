package org.demo.shop1.modules.categories.adapters.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategorySaveResponseDTO extends CategoryDTO{

    private String description;
    private Boolean isActive;
}
