package com.stream.video.catalog.domain.exceptions;

import com.stream.video.catalog.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException{
    private List<Error> errors;

    private DomainException (final List<Error> errors) {
        super("", null, true, false);
        this.errors = errors;
    }

    public static DomainException with (final Error error) {
        return new DomainException(List.of(error));
    }

    public static DomainException with (final List<Error> errors) {
        return new DomainException(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
