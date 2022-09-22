package com.stream.video.catalog.domain;

import com.stream.video.catalog.domain.category.Category;
import com.stream.video.catalog.domain.exceptions.DomainException;
import com.stream.video.catalog.domain.validation.handler.ThrowsValidationHandler;
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

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldRaiseException(){
        final String expectedName = null;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = "The most watched category";
        final var expectedIsActive = true;

        final var category = new Category(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
    }
}
