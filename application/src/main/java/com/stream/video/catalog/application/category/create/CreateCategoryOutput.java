package com.stream.video.catalog.application.category.create;

import com.stream.video.catalog.domain.category.Category;
import com.stream.video.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {
    public static CreateCategoryOutput from (final Category category){
        return new CreateCategoryOutput(category.getId());
    }

}
