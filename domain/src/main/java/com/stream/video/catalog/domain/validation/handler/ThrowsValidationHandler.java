package com.stream.video.catalog.domain.validation.handler;

import com.stream.video.catalog.domain.exceptions.DomainException;
import com.stream.video.catalog.domain.validation.Error;
import com.stream.video.catalog.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(ValidationHandler handler) {
        throw DomainException.with(handler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validation) {
       try {
           validation.validate();
       } catch (final Exception e) {
           throw DomainException.with(List.of(new Error(e.getMessage())));
       }
       return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }
}
