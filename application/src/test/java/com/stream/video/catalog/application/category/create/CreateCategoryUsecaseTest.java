package com.stream.video.catalog.application.category.create;

import com.stream.video.catalog.domain.category.Category;
import com.stream.video.catalog.domain.category.CategoryGateway;
import com.stream.video.catalog.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUsecaseTest {

    @Mock
    CategoryGateway categoryGateway;

    @InjectMocks
    DefaultCreateCategoryUseCase useCase;

    @Test
    public void givenAValidCommand_whenCreateCategory_thenReturnCategoryID (){
        final var expectedName = "My category";
        final var expectedDescription = "The most watched one";
        final var expectedIsActive = true;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        //final var categoryGateway = Mockito.mock(CategoryGateway.class);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        //final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(category -> {
                    return Objects.equals(expectedName, category.getName())
                            && Objects.equals(expectedDescription, category.getDescription())
                            && Objects.equals(expectedIsActive, category.isActive())
                            && Objects.nonNull(category.getCreatedAt())
                            && Objects.nonNull(category.getUpdatedAt())
                            && Objects.isNull(category.getDeletedAt());
                }));
    }

    @Test
    public void givenInvalidName_whenCreateCategory_thenReturnException () {
        final String expectedName = null;
        final var expectedDescription = "The most watched one";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(DomainException.class, () -> useCase.execute(command));

        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
        Mockito.verify(categoryGateway, Mockito.times(0)).create(Mockito.any());

    }

    @Test
    public void givenValidCommandWithInactiveCategory_whenCreateCategory_thenReturnInactiveCategoryId () {
        final var expectedName = "My category";
        final var expectedDescription = "The most watched one";
        final var expectedIsActive = false;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        //final var categoryGateway = Mockito.mock(CategoryGateway.class);

        Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

        //final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);

        final var output = useCase.execute(command);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(category -> {
                    return Objects.equals(expectedName, category.getName())
                            && Objects.equals(expectedDescription, category.getDescription())
                            && Objects.equals(expectedIsActive, category.isActive())
                            && Objects.nonNull(category.getCreatedAt())
                            && Objects.nonNull(category.getUpdatedAt())
                            && Objects.nonNull(category.getDeletedAt());
                }));
    }

    @Test
    public void givenValidCommandWithInactiveCategory_whenExceptionIsRaised_thenThrowException () {
        final var expectedName = "My category";
        final var expectedDescription = "The most watched one";
        final var expectedIsActive = true;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any())).thenThrow(new IllegalStateException("Random exception"));

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(command));

        Assertions.assertNotNull(exception);

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(category -> {
                    return Objects.equals(expectedName, category.getName())
                            && Objects.equals(expectedDescription, category.getDescription())
                            && Objects.equals(expectedIsActive, category.isActive())
                            && Objects.nonNull(category.getCreatedAt())
                            && Objects.nonNull(category.getUpdatedAt())
                            && Objects.isNull(category.getDeletedAt());
                }));
    }

}
