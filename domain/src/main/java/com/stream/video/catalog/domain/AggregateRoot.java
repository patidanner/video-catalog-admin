package com.stream.video.catalog.domain;

public class AggregateRoot <ID extends Identifier> extends Entity{
    protected AggregateRoot(final ID id) {
        super(id);
    }
}
