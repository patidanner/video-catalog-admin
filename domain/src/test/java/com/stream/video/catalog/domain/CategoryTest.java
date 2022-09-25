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
        Assertions.assertNotNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallDeactivate_thenReturnCategoryInactive() {
        final String categoryName = "Comedy";
        final String categoryDescription = " ";
        final var expectedIsActive = false;

        final var category = new Category(categoryName, categoryDescription, true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedAt = category.getUpdatedAt();

        Assertions.assertNull(category.getDeletedAt());
        Assertions.assertTrue(category.isActive());

        final var inactiveCategory = category.deactivate();

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(category.getId(), inactiveCategory.getId());
        Assertions.assertEquals(categoryName, category.getName());
        Assertions.assertEquals(categoryDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertTrue(category.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActive() {
        final String categoryName = "Comedy";
        final String categoryDescription = " ";
        final var expectedIsActive = true;

        final var category = new Category(categoryName, categoryDescription, false);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedAt = category.getUpdatedAt();

        Assertions.assertNotNull(category.getDeletedAt());
        Assertions.assertFalse(category.isActive());

        final var inactiveCategory = category.activate();

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(category.getId(), inactiveCategory.getId());
        Assertions.assertEquals(categoryName, category.getName());
        Assertions.assertEquals(categoryDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertTrue(category.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateActive_thenReturnCategoryUpdated() {
        final String expectedCategoryName = "Comedy";
        final String expectedCategoryDescription = "The category description";
        final var expectedIsActive = true;

        final var oldCategory = new Category("Old Category Name", "old description", false);

        Assertions.assertDoesNotThrow(() -> oldCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = oldCategory.getUpdatedAt();
        final var createdAt = oldCategory.getCreatedAt();

        final var updatedCategory = oldCategory.update(expectedCategoryName, expectedCategoryDescription, expectedIsActive);

        Assertions.assertEquals(oldCategory.getId(), updatedCategory.getId());
        Assertions.assertEquals(expectedCategoryName, updatedCategory.getName());
        Assertions.assertEquals(expectedCategoryDescription, updatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, updatedCategory.isActive());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(updatedCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateInactive_thenReturnCategoryUpdated() {
        final String expectedCategoryName = "Comedy";
        final String expectedCategoryDescription = "The category description";
        final var expectedIsActive = false;

        final var oldCategory = new Category("Old Category Name", "old description", true);

        Assertions.assertDoesNotThrow(() -> oldCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = oldCategory.getUpdatedAt();
        final var createdAt = oldCategory.getCreatedAt();

        final var updatedCategory = oldCategory.update(expectedCategoryName, expectedCategoryDescription, expectedIsActive);

        Assertions.assertEquals(oldCategory.getId(), updatedCategory.getId());
        Assertions.assertEquals(expectedCategoryName, updatedCategory.getName());
        Assertions.assertEquals(expectedCategoryDescription, updatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, updatedCategory.isActive());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(updatedCategory.getDeletedAt());
    }

    @Test
    // Validates that update method should not call validate();
    public void givenAnIvalidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final String expectedCategoryName = null;
        final String expectedCategoryDescription = "The category description";
        final var expectedIsActive = false;

        final var oldCategory = new Category("Old Category Name", "old description", true);

        final var updatedAt = oldCategory.getUpdatedAt();
        final var createdAt = oldCategory.getCreatedAt();

        final var updatedCategory = oldCategory.update(expectedCategoryName, expectedCategoryDescription, expectedIsActive);

        Assertions.assertEquals(oldCategory.getId(), updatedCategory.getId());
        Assertions.assertEquals(expectedCategoryName, updatedCategory.getName());
        Assertions.assertEquals(expectedCategoryDescription, updatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, updatedCategory.isActive());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(updatedCategory.getDeletedAt());
    }
}
