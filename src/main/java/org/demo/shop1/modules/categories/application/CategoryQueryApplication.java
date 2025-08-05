package org.demo.shop1.modules.categories.application;

import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.exceptions.CategoryQueryException;
import org.demo.shop1.modules.categories.domain.models.Category;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryQueryOutRepository;
import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryQueryApplication extends CategoryApplication implements CategoryQueryService {

    private final CategoryQueryOutRepository categoryQueryOutRepository;

    @PostConstruct
    public void init() {
        nameClass = "CategoryQueryApplication";
    }

    @Override
    public Mono<Category> findById(Integer id) {
        return Mono.defer(() -> (id == null || id <= 0)
                ? Mono.error(new CategoryQueryException(
                        CategoryMessageEnum.ID_INVALID.code,
                        CategoryMessageEnum.ID_INVALID.message))
                : categoryQueryOutRepository.findById(id))
                .doFirst(() -> startMethod("findById"))
                .doOnSuccess(category -> endMethod("findById"));
    }

}
