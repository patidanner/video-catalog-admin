package com.stream.video.catalog.domain.category;

import com.stream.video.catalog.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {
    Category create (Category category);
    void deletebyId(CategoryID id);
    Optional<Category> findById(CategoryID id);
    Optional<Category> update(Category category);
    Pagination<Category> findAll(CategorySearchQuery query);
}
