package com.stream.video.catalog.application;

import com.stream.video.catalog.domain.category.Category;

/**
 * use cases using Pattern Command
 *
 * The use case receives an input and returns an output.
 */
public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN in);
}