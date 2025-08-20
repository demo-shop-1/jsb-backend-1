package org.demo.shop1.modules.categories.application;

import org.demo.shop1.modules.categories.domain.Category;
import org.demo.shop1.modules.categories.domain.enums.CategoryMessageEnum;
import org.demo.shop1.modules.categories.domain.ports.out.CategoryQueryOutRepository;
import org.demo.shop1.modules.categories.domain.services.CategoryQueryService;
import org.demo.shop1.modules.categories.domain.utils.CategoryUtil;
import org.demo.shop1.utils.ObjectUtil;
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
                ? CategoryUtil.throwQueryError(CategoryMessageEnum.ID_INVALID)
                : categoryQueryOutRepository.findById(id))
                .cast(Category.class)
                .doFirst(() -> startMethod("findById"))
                .doFinally(category -> endMethod("findById"));
    }

    @Override
    public Mono<Category> findByName(String name) {
        return Mono.defer(() -> ObjectUtil.isBlankString(name)
                ? CategoryUtil.throwQueryError(CategoryMessageEnum.NAME_BLANK)
                : categoryQueryOutRepository.findByName(name))
                .cast(Category.class)
                .doFirst(() -> startMethod("findByName"))
                .doFinally(category -> endMethod("findByName"));
    }

}
