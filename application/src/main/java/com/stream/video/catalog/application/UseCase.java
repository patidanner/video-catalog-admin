package com.stream.video.catalog.application;

import com.stream.video.catalog.domain.category.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}