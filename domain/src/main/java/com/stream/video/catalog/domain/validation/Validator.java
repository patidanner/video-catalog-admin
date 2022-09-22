package com.stream.video.catalog.domain.validation;

public abstract class Validator {
    private ValidationHandler handler;

    protected Validator (final ValidationHandler handler) {
        this.handler = handler;
    }

    protected ValidationHandler validationHandler() {
        return this.handler;
    }

    protected abstract void validate();
}
