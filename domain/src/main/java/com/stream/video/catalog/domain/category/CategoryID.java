package com.stream.video.catalog.domain.category;

import com.stream.video.catalog.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {
    private final String value;

    public CategoryID(String value) {
        this.value = value;
    }

    public static CategoryID unique() {
        return new CategoryID(UUID.randomUUID().toString().toLowerCase());
    }

    public static CategoryID from(final String anId){
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID anId) {
        return new CategoryID(anId.toString().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String getValue() {
        return value;
    }
}
