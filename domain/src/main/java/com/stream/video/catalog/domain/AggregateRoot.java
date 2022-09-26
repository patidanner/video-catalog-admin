package com.stream.video.catalog.domain;

import com.stream.video.catalog.domain.validation.ValidationHandler;

public class AggregateRoot <ID extends Identifier> extends Entity<ID>{
    protected AggregateRoot(final ID id) {
        super(id);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
