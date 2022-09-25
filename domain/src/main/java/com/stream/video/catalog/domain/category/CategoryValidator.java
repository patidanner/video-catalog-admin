package com.stream.video.catalog.domain.category;

import com.stream.video.catalog.domain.validation.Error;
import com.stream.video.catalog.domain.validation.ValidationHandler;
import com.stream.video.catalog.domain.validation.Validator;

/*
* Validators can change during time, that's why it is kept
* separated from the Entity itself.
* However, to follow DDD, this CategoryValidator is called from
* the Entity itself, from a "Validate" method available in Category entity.
* */
public class CategoryValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    protected void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final String name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must have between 3 and 255 characters"));
        }
    }
}
