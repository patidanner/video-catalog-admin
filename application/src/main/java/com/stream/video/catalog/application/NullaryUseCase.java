package com.stream.video.catalog.application;

/**
 * Does not receive input
 * @param <OUT>
 */
public abstract class NullaryUseCase<OUT> {
    public abstract OUT execute();
}
