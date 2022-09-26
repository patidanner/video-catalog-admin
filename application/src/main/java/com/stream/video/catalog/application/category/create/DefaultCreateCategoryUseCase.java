package com.stream.video.catalog.application.category.create;

import com.stream.video.catalog.domain.category.Category;
import com.stream.video.catalog.domain.category.CategoryGateway;
import com.stream.video.catalog.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand in) {
        final var newCategory = Category.newCategory(
                in.name(),
                in.description(),
                in.isActive()
        );

        newCategory.validate(new ThrowsValidationHandler());
        return CreateCategoryOutput.from(categoryGateway.create(newCategory));
    }
}
