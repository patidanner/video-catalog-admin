package com.stream.video.catalog.domain;

import com.stream.video.catalog.domain.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParam_whenCallNewCategory_thenInstantiateACategory(){
        final var expectedName = "Comedy";
        final var expectedDescription = "For fun!";
        final var expectedIsActive = true;

        final var newCategory = new Category(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());
    }
}
