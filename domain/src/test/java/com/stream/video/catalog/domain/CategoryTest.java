package com.stream.video.catalog.domain;

import com.stream.video.catalog.domain.category.Category;
import com.stream.video.catalog.domain.exceptions.DomainException;
import com.stream.video.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParam_whenCallNewCategory_thenInstantiateACategory(){
        final var categoryName = "Comedy";
        final var expectedDescription = "For fun!";
        final var expectedIsActive = true;

        final var newCategory = new Category(categoryName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(categoryName, newCategory.getName());
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

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldRaiseException(){
        final String expectedName = " ";
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedDescription = "The most watched category";
        final var expectedIsActive = true;

        final var category = new Category(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldRaiseException(){
        final String expectedName = "Mo ";
        final var expectedErrorMessage = "'name' must have between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var categoryDescription = "The most watched category";
        final var expectedIsActive = true;

        final var category = new Category(expectedName, categoryDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldRaiseException(){
        final String expectedName = """
               A prática cotidiana prova que o fenômeno da Internet apresenta tendências no sentido de aprovar a manutenção do levantamento das variáveis envolvidas.
               A prática cotidiana prova que o fenômeno da Internet apresenta tendências no sentido de aprovar a manutenção do levantamento das variáveis envolvidas.
               """;
        final var expectedErrorMessage = "'name' must have between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var categoryDescription = "The most watched category";
        final var expectedIsActive = true;

        final var category = new Category(expectedName, categoryDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenCreateCategory(){
        final String categoryName = "Comedy";
        final String categoryDescription = " ";
        final var expectedIsActive = true;

        final var category = new Category(categoryName, categoryDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(categoryName, category.getName());
        Assertions.assertEquals(categoryDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidIsNotActive_whenCallNewCategoryAndValidate_thenCreateCategory(){
        final String categoryName = "Comedy";
        final String categoryDescription = " ";
        final var expectedIsActive = false;

        final var category = new Category(categoryName, categoryDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(categoryName, category.getName());
        Assertions.assertEquals(categoryDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }
}
